<script setup>
import { useToast } from '../composables/useToast'

const { toasts, remover } = useToast()
</script>

<template>
    <div class="toast-container">
        <TransitionGroup name="toast">
            <div v-for="t in toasts" :key="t.id" class="toast" :class="t.tipo" @click="remover(t.id)">
                <span class="mensagem">{{ t.mensagem }}</span>
            </div>
        </TransitionGroup>
    </div>
</template>

<style scoped>
.toast-container {
    position: fixed;
    top: 1rem;
    right: 1rem;
    display: flex;
    flex-direction: column;
    gap: .5rem;
    z-index: 1000;
    pointer-events: none;
}

.toast {
    display: flex;
    align-items: center;
    gap: .75rem;
    min-width: 280px;
    max-width: 400px;
    padding: .85rem 1rem;
    border-radius: 6px;
    background: #fff;
    color: #fff;
    box-shadow: 0 4px 12px rgba(0, 0, 0, .15);
    cursor: pointer;
    pointer-events: auto;
    font-size: .9rem;
    border-left: 4px solid;
}

.toast.sucesso {
    background: #2e7d32;
    border-left-color: #1b5e20;
}

.toast.erro {
    background: #c62828;
    border-left-color: #8e0000;
}

.toast.aviso {
    background: #f9a825;
    border-left-color: #c17900;
    color: #2d2000;
}

.toast.info {
    background: #1976d2;
    border-left-color: #0d47a1;
}

.icone {
    font-size: 1.1rem;
    font-weight: bold;
    flex-shrink: 0;
}

.mensagem {
    flex: 1;
}

.toast-enter-active,
.toast-leave-active {
    transition: all .3s ease;
}

.toast-enter-from {
    opacity: 0;
    transform: translateX(50px);
}

.toast-leave-to {
    opacity: 0;
    transform: translateX(50px);
}

.toast-leave-active {
    position: absolute;
}
</style>