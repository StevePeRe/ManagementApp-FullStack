import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import TaskCreateEdit from '../views/TaskCreateEdit.vue'
import TaskDetailView from '../views/TaskDetailView.vue'

const routes = [
  { path: '/', name: 'Home', component: HomeView },
  { 
    path: '/tasks/new', 
    name: 'CreateTask', 
    component: TaskCreateEdit 
  },
  { 
    path: '/tasks/:id/edit', 
    name: 'EditTask', 
    component: TaskCreateEdit,
    props: true
  },
  { 
    path: '/tasks/:id', 
    name: 'TaskDetail', 
    component: TaskDetailView,
    props: true
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router