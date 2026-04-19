<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" md="8">
        <v-card v-if="task">
          <v-card-title>
            {{ task.name }}
            <v-spacer></v-spacer>
            <v-btn 
              icon="mdi-arrow-left"
              @click="$router.back()"
              variant="text"
            ></v-btn>
          </v-card-title>
          
          <v-card-text>
            <v-chip :color="getStateColor(task.state)" class="mb-4">
              {{ task.state }}
            </v-chip>
            
            <div>
              <strong>ID:</strong> {{ task.id }}
            </div>
          </v-card-text>
          
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn 
              color="primary"
              :to="{ name: 'EditTask', params: { id: task.id } }"
            >
              Editar
            </v-btn>
          </v-card-actions>
        </v-card>
        
        <v-card v-else>
          <v-card-text>Cargando...</v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { taskService } from '@/services/api'

const route = useRoute()
const task = ref(null)

onMounted(async () => {
  try {
    task.value = await taskService.getById(route.params.id)
  } catch (error) {
    console.error('Error loading task detail:', error)
  }
})

const getStateColor = (state) => {
  return state === 'DONE' ? 'success' : 
         state === 'RUNNING' ? 'warning' : 'info'
}
</script>
