# Build stage
FROM node:20-alpine AS build
WORKDIR /app

# Instalamos dependencias
COPY package*.json ./
RUN npm ci

# Copiamos todo el código y construimos
COPY . .
RUN npm run build

# Production stage
FROM nginx:alpine
# Copiamos la build de Vue al nginx
COPY --from=build /app/dist /usr/share/nginx/html

# Configuración mínima para Vue SPA
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]