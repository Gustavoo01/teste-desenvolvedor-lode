<script setup>
import { useConfirm } from '../composables/useConfirm'

const { estado, responder } = useConfirm()

function aoTeclar(e) {
    if (!estado.value) return
    if (e.key === 'Escape') responder(false)
    if (e.key === 'Enter') responder(true)
}
</script>

<template>
    <Transition name="fade">
        <div v-if="estado" class="overlay" @click.self="responder(false)" @keydown="aoTeclar" tabindex="0"
            ref="overlayRef">
            <div class="dialog" :class="estado.tipoConfirmacao">
                <h2>{{ estado.titulo }}</h2>
                <p>{{ estado.mensagem }}</p>

                <div class="acoes">
                    <button class="btn-cancel" @click="responder(false)">
                        {{ estado.textoCancelar }}
                    </button>
                    <button class="btn-confirm" :class="estado.tipoConfirmacao" @click="responder(true)" autofocus>
                        {{ estado.textoConfirmar }}
                    </button>
                </div>
            </div>
        </div>
    </Transition>
</template>

<style scoped>
.overlay {
    position: fixed;
    inset: 0;
    background: rgba(0, 0, 0, .55);
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 200;
    outline: none;
}

.dialog {
    background: #fff;
    border-radius: 10px;
    padding: 1.75rem;
    width: 90%;
    max-width: 420px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, .25);
    text-align: center;
    animation: pop .25s ease;
}

@keyframes pop {
    from {
        transform: scale(.9);
        opacity: 0;
    }

    to {
        transform: scale(1);
        opacity: 1;
    }
}

.dialog h2 {
    margin: 0 0 .5rem;
    font-size: 1.25rem;
    color: #263238;
}

.dialog p {
    margin: 0 0 1.5rem;
    color: #555;
    font-size: .95rem;
    line-height: 1.4;
}

.acoes {
    display: flex;
    gap: .75rem;
    justify-content: center;
}

.acoes button {
    flex: 1;
    max-width: 140px;
    padding: .6rem 1rem;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    font-size: .95rem;
    font-weight: 500;
    transition: opacity .2s, transform .1s;
}

.acoes button:hover {
    opacity: .9;
}

.acoes button:active {
    transform: scale(.98);
}

.btn-cancel {
    background: #eceff1;
    color: #455a64;
}

.btn-confirm.primario {
    background: #1976d2;
    color: #fff;
}

.btn-confirm.perigo {
    background: #c62828;
    color: #fff;
}

.fade-enter-active,
.fade-leave-active {
    transition: opacity .2s ease;
}

.fade-enter-from,
.fade-leave-to {
    opacity: 0;
}
</style>