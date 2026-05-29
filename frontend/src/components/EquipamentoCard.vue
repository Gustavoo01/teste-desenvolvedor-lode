<script setup>
defineProps({
    equipamento: { type: Object, required: true }
})

defineEmits(['editar', 'remover'])

const statusLabels = {
    OPERACIONAL: 'Operacional',
    MANUTENCAO_NECESSARIA: 'Manutenção Necessária',
    CRITICO: 'Crítico'
}
</script>

<template>
    <div class="card" :class="equipamento.status.toLowerCase()">
        <div class="card-header">
            <h2>{{ equipamento.nome }}</h2>
            <span class="badge">{{ statusLabels[equipamento.status] }}</span>
        </div>

        <div class="card-body">
            <p><strong>Tipo:</strong> {{ equipamento.tipo || '—' }}</p>
            <p><strong>Instalação:</strong> {{ equipamento.dataInstalacao || '—' }}</p>
        </div>

        <div class="card-actions">
            <button class="btn-edit" @click="$emit('editar', equipamento)">Editar</button>
            <button class="btn-delete" @click="$emit('remover', equipamento)">Remover</button>
        </div>
    </div>
</template>

<style scoped>
.card {
    padding: 1rem;
    border-radius: 8px;
    border-left: 6px solid #888;
    background: #fff;
    box-shadow: 0 1px 3px rgba(0, 0, 0, .08);
    display: flex;
    flex-direction: column;
    gap: .75rem;
}

.card.operacional {
    border-left-color: #2e7d32;
}

.card.manutencao_necessaria {
    border-left-color: #f9a825;
}

.card.critico {
    border-left-color: #c62828;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    gap: .5rem;
}

.card-header h2 {
    margin: 0;
    font-size: 1.1rem;
}

.card-body p {
    margin: .25rem 0;
    font-size: .9rem;
    color: #555;
}

.badge {
    padding: .25rem .6rem;
    border-radius: 12px;
    font-size: .75rem;
    color: #fff;
    background: #888;
    white-space: nowrap;
}

.card.operacional .badge {
    background: #2e7d32;
}

.card.manutencao_necessaria .badge {
    background: #f9a825;
}

.card.critico .badge {
    background: #c62828;
}

.card-actions {
    display: flex;
    gap: .5rem;
    margin-top: auto;
}

.card-actions button {
    flex: 1;
    padding: .4rem;
    border: none;
    border-radius: 4px;
    font-size: .85rem;
    cursor: pointer;
    transition: opacity .2s;
}

.card-actions button:hover {
    opacity: .85;
}

.btn-edit {
    background: #1976d2;
    color: #fff;
}

.btn-delete {
    background: #c62828;
    color: #fff;
}
</style>