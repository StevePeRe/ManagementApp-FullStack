import axios from 'axios'

const api = axios.create({
  baseURL: '/api',
  headers: {
    'Content-Type': 'application/json',
  },
})

export const taskService = {
  async getAll() {
    const response = await api.get('/tasks')
    return response.data
  },
  
  async getById(id) {
    const response = await api.get(`/tasks/${id}`)
    return response.data
  },
  
  async create(task) {
    const response = await api.post('/tasks', task);
    console.log('Response:', response.data);
  },
  
  async update(id, task) {
    const response = await api.put(`/tasks/${id}`, task)
    return response.data
  },
  
  async delete(id) {
    await api.delete(`/tasks/${id}`)
  }
}

export default api