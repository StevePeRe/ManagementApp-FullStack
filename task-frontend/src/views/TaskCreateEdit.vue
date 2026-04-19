<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" md="8">
        <v-card>
          <v-card-title>
            {{ isEdit ? 'Editar Tarea' : 'Nueva Tarea' }}
            <v-spacer></v-spacer>
            <v-btn 
              icon="mdi-arrow-left"
              @click="$router.back()"
              variant="text"
            ></v-btn>
          </v-card-title>
          
          <v-card-text>
            <v-form @submit.prevent="saveTask">
              <v-text-field
                v-model="task.name"
                label="Nombre *"
                :error-messages="nameErrors"
                required
              ></v-text-field>
              
              <v-select
                v-model="task.state"
                :items="states"
                label="Estado *"
                required
              ></v-select>
              
              <v-btn 
                type="submit"
                color="primary"
                :loading="saving"
                block
              >
                Guardar
              </v-btn>
            </v-form>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { taskService } from '@/services/api'

const route = useRoute()
const router = useRouter()

const task = ref({
  name: '',
  state: 'CREATED'
})

const states = ['CREATED', 'RUNNING', 'DONE']
const saving = ref(false)
const isEdit = computed(() => !!route.params.id)

const nameErrors = computed(() => {
  const errors = []
  if (!task.value.name) errors.push('El nombre es requerido')
  return errors
})

onMounted(async () => {
  if (isEdit.value) {
    try {
      const taskData = await taskService.getById(route.params.id)
      task.value = taskData;  // ← ¿Esto funciona?
    } catch (error) {
      console.error('Error loading task:', error)
    }
  }
})

const saveTask = async () => {
  if (!task.value.name) return
  
  saving.value = true
  try {
    if (isEdit.value) {
      await taskService.update(route.params.id, {
        name: task.value.name,
        state: task.value.state
      })
    } else {
      await taskService.create({
        name: task.value.name,
        state: task.value.state
      })
    }
    router.push({ name: 'Home' })
  } catch (error) {
    console.error('Error saving task:', error)
  } finally {
    saving.value = false
  }
}
</script>
