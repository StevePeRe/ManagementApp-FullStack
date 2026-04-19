<template>
  <v-container>
    <v-row>
      <v-col>
        <v-btn 
          color="primary" 
          @click="$router.push({ name: 'CreateTask' })"
          class="mb-4"
        >
          Nueva Tarea
        </v-btn>
        
        <v-data-table
          :headers="headers"
          :items="tasks"
          :loading="loading"
          class="elevation-1"
        >
          <template #item.actions="{ item }">
            <v-btn 
              icon="mdi-eye" 
              variant="text"
              title="Ver detalles"
              @click="viewTask(item.id)"
              size="small"
            ></v-btn>
            <v-btn 
              icon="mdi-pencil" 
              variant="text"
              title="Editar tarea"
              @click="editTask(item.id)"
              size="small"
            ></v-btn>
            <v-btn 
              icon="mdi-delete" 
              variant="text"
              title="Eliminar tarea"
              color="error"
              @click="confirmDelete(item.id)"
              size="small"
            ></v-btn>
          </template>
          
          <template #item.state="{ item }">
            <v-chip 
              :color="getStateColor(item.state)"
              size="small"
            >
              {{ item.state }}
            </v-chip>
          </template>
        </v-data-table>
      </v-col>
    </v-row>
    
    <!-- Diálogo de confirmación eliminación -->
    <v-dialog v-model="deleteDialog" max-width="400">
      <v-card>
        <v-card-title>¿Confirmar eliminación?</v-card-title>
        <v-card-text>Esta acción no se puede deshacer.</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="deleteDialog = false">Cancelar</v-btn>
          <v-btn color="error" @click="deleteTask">Eliminar</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { taskService } from '@/services/api'
import { useRouter } from 'vue-router'

const router = useRouter()
const tasks = ref([])
const loading = ref(false)
const deleteDialog = ref(false)
const taskToDelete = ref(null)

const headers = [
  { title: 'Nombre', key: 'name' },
  { title: 'Estado', key: 'state' },
  { title: 'Acciones', key: 'actions', sortable: false }
]

onMounted(async () => {
  await loadTasks()
})

const loadTasks = async () => {
  loading.value = true
  try {
    tasks.value = await taskService.getAll()
  } finally {
    loading.value = false
  }
}

const viewTask = (id) => {
  router.push({ name: 'TaskDetail', params: { id } })
}

const editTask = (id) => {
  router.push({ name: 'EditTask', params: { id } })
}

const confirmDelete = (id) => {
  taskToDelete.value = id
  deleteDialog.value = true
}

const deleteTask = async () => {
  try {
    await taskService.delete(taskToDelete.value)
    await loadTasks()
  } catch (error) {
    console.error('Error deleting task:', error)
  } finally {
    deleteDialog.value = false
    taskToDelete.value = null
  }
}

const getStateColor = (state) => {
  const stateColors = {
    'CREATED': 'warning',
    'RUNNING': 'error',
    'COMPLETED': 'success'
  }
  return stateColors[state] || 'secondary'
}
</script>