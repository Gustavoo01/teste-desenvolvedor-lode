import { ref } from "vue";

const toasts = ref([]);
let proximoId = 0;

export function useToast() {
  function mostrar(mensagem, tipo = "info", duracao = 3500) {
    const id = proximoId++;
    toasts.value.push({ id, mensagem, tipo });
    setTimeout(() => remover(id), duracao);
  }

  function remover(id) {
    toasts.value = toasts.value.filter((t) => t.id !== id);
  }

  return {
    toasts,
    sucesso: (msg) => mostrar(msg, "sucesso"),
    erro: (msg) => mostrar(msg, "erro", 5000),
    aviso: (msg) => mostrar(msg, "aviso"),
    info: (msg) => mostrar(msg, "info"),
    remover,
  };
}
