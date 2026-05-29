<script setup>
import { ref, computed, onMounted } from 'vue'
import AppHeader from './components/AppHeader.vue'
import AppFooter from './components/AppFooter.vue'
import EquipamentoCard from './components/EquipamentoCard.vue'
import EquipamentoForm from './components/EquipamentoForm.vue'
import { useEquipamentos } from './composables/useEquipamento'

const { equipamentos, carregando, erro, carregar, criar, atualizar, remover } = useEquipamentos()

const busca = ref('')
const modalAberto = ref(false)
const equipamentoEditando = ref(null)

const equipamentosFiltrados = computed(() =>
  equipamentos.value.filter(e =>
    e.nome.toLowerCase().includes(busca.value.toLowerCase())
  )
)

onMounted(carregar)

function abrirNovo() {
  equipamentoEditando.value = null
  modalAberto.value = true
}

function abrirEdicao(equipamento) {
  equipamentoEditando.value = equipamento
  modalAberto.value = true
}

async function salvar(dados) {
  try {
    if (dados.id) {
      await atualizar(dados.id, dados)
    } else {
      await criar(dados)
    }
    modalAberto.value = false
  } catch (e) {
    alert('Erro ao salvar: ' + (e.response?.data?.nome || e.message))
  }
}

async function confirmarRemocao(equipamento) {
  if (!confirm(`Remover "${equipamento.nome}"?`)) return
  try {
    await remover(equipamento.id)
  } catch (e) {
    alert('Erro ao remover: ' + e.message)
  }
}
</script>

<template>
  <AppHeader />

  <main class="container">
    <div class="toolbar">
      <input v-model="busca" type="text" placeholder="Buscar por nome..." class="busca" />
      <button class="btn-novo" @click="abrirNovo">+ Novo Equipamento</button>
    </div>

    <p v-if="carregando">Carregando...</p>
    <p v-else-if="erro" class="erro">{{ erro }}</p>
    <p v-else-if="equipamentosFiltrados.length === 0">Nenhum equipamento encontrado.</p>

    <div class="grid">
      <EquipamentoCard v-for="eq in equipamentosFiltrados" :key="eq.id" :equipamento="eq" @editar="abrirEdicao"
        @remover="confirmarRemocao" />
    </div>
  </main>

  <AppFooter />

  <EquipamentoForm v-model="modalAberto" :equipamento="equipamentoEditando" @salvar="salvar" />
</template>

<style>
body {
  margin: 0;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
  background: #f5f7fa;
}

.container {
  max-width: 900px;
  margin: 0 auto;
  padding: 2rem;
}

.toolbar {
  display: flex;
  gap: 1rem;
  margin-bottom: 1.5rem;
}

.busca {
  flex: 1;
  padding: .75rem;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 6px;
}

.btn-novo {
  padding: .75rem 1.25rem;
  background: #2e7d32;
  color: #fff;
  border: none;
  border-radius: 6px;
  font-size: .95rem;
  cursor: pointer;
}

.btn-novo:hover {
  background: #1b5e20;
}

.grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 1rem;
}

.erro {
  color: #c62828;
}
</style>