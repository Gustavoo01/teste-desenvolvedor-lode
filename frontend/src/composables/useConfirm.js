import { ref } from "vue";

const estado = ref(null);
let resolverPromise = null;

export function useConfirm() {
  function confirmar(opcoes) {
    estado.value = {
      titulo: opcoes.titulo || "Confirmar",
      mensagem: opcoes.mensagem || "Tem certeza?",
      textoConfirmar: opcoes.textoConfirmar || "Confirmar",
      textoCancelar: opcoes.textoCancelar || "Cancelar",
      tipoConfirmacao: opcoes.tipoConfirmacao || "primario",
    };

    return new Promise((resolve) => {
      resolverPromise = resolve;
    });
  }

  function responder(resposta) {
    estado.value = null;
    if (resolverPromise) {
      resolverPromise(resposta);
      resolverPromise = null;
    }
  }

  return { estado, confirmar, responder };
}
