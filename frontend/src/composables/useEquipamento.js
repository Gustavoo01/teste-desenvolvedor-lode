import { ref } from "vue";
import api from "../services/api";

export function useEquipamentos() {
  const equipamentos = ref([]);
  const carregando = ref(false);
  const erro = ref(null);

  async function carregar() {
    carregando.value = true;
    erro.value = null;
    try {
      const { data } = await api.get("/equipamentos");
      equipamentos.value = data;
    } catch (e) {
      erro.value = "Erro ao carregar equipamentos";
      console.error(e);
    } finally {
      carregando.value = false;
    }
  }

  async function criar(equipamento) {
    const { data } = await api.post("/equipamentos", equipamento);
    equipamentos.value.push(data);
    return data;
  }

  async function atualizar(id, equipamento) {
    const { data } = await api.put(`/equipamentos/${id}`, equipamento);
    const idx = equipamentos.value.findIndex((e) => e.id === id);
    if (idx !== -1) equipamentos.value[idx] = data;
    return data;
  }

  async function remover(id) {
    await api.delete(`/equipamentos/${id}`);
    equipamentos.value = equipamentos.value.filter((e) => e.id !== id);
  }

  return {
    equipamentos,
    carregando,
    erro,
    carregar,
    criar,
    atualizar,
    remover,
  };
}
