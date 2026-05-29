<script setup>
import { ref, watch } from 'vue'

const props = defineProps({
    modelValue: { type: Boolean, default: false },
    equipamento: { type: Object, default: null }
})

const emit = defineEmits(['update:modelValue', 'salvar'])

const form = ref(estadoInicial())
const erros = ref({})

function estadoInicial() {
    return {
        id: null,
        nome: '',
        tipo: '',
        dataInstalacao: '',
        status: 'OPERACIONAL'
    }
}

watch(
    () => props.equipamento,
    (novo) => {
        form.value = novo ? { ...novo } : estadoInicial()
        erros.value = {}
    },
    { immediate: true }
)

function fechar() {
    emit('update:modelValue', false)
}

function validar() {
    erros.value = {}
    if (!form.value.nome.trim()) erros.value.nome = 'Nome é obrigatório'
    return Object.keys(erros.value).length === 0
}

function submeter() {
    if (!validar()) return
    emit('salvar', { ...form.value })
}
</script>

<template>
    <div v-if="modelValue" class="overlay" @click.self="fechar">
        <div class="modal">
            <h2>{{ form.id ? 'Editar Equipamento' : 'Novo Equipamento' }}</h2>

            <div class="campo">
                <label>Nome *</label>
                <input v-model="form.nome" type="text" />
                <span v-if="erros.nome" class="erro">{{ erros.nome }}</span>
            </div>

            <div class="campo">
                <label>Tipo</label>
                <input v-model="form.tipo" type="text" placeholder="Ex: Bomba, Painel..." />
            </div>

            <div class="campo">
                <label>Data de Instalação</label>
                <input v-model="form.dataInstalacao" type="date" />
            </div>

            <div class="campo">
                <label>Status</label>
                <select v-model="form.status">
                    <option value="OPERACIONAL">Operacional</option>
                    <option value="MANUTENCAO_NECESSARIA">Manutenção Necessária</option>
                    <option value="CRITICO">Crítico</option>
                </select>
            </div>

            <div class="acoes">
                <button class="btn-cancel" @click="fechar">Cancelar</button>
                <button class="btn-save" @click="submeter">Salvar</button>
            </div>
        </div>
    </div>
</template>

<style scoped>
.overlay {
    position: fixed;
    inset: 0;
    background: rgba(0, 0, 0, .5);
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 100;
}

.modal {
    background: #fff;
    border-radius: 8px;
    padding: 1.5rem;
    width: 90%;
    max-width: 450px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, .2);
}

.modal h2 {
    margin: 0 0 1rem;
    font-size: 1.2rem;
}

.campo {
    margin-bottom: 1rem;
    display: flex;
    flex-direction: column;
    gap: .25rem;
}

.campo label {
    font-size: .85rem;
    font-weight: 600;
    color: #555;
}

.campo input,
.campo select {
    padding: .5rem;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: .95rem;
}

.erro {
    color: #c62828;
    font-size: .8rem;
}

.acoes {
    display: flex;
    gap: .5rem;
    justify-content: flex-end;
    margin-top: 1.5rem;
}

.acoes button {
    padding: .5rem 1rem;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: .9rem;
}

.btn-cancel {
    background: #eee;
    color: #333;
}

.btn-save {
    background: #1976d2;
    color: #fff;
}
</style>