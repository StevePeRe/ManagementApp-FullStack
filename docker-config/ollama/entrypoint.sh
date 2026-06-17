#!/bin/sh

# Start Ollama server in background
ollama serve &
SERVER_PID=$!

# Wait for server to be ready
echo "Waiting for Ollama server to start..."
for i in $(seq 1 30); do
    if curl -s http://localhost:11434 > /dev/null 2>&1; then
        echo "Ollama server is ready!"
        break
    fi
    echo "Waiting... ($i/30)"
    sleep 2
done

# Pull the model
echo "Downloading model llama3.2..."
ollama pull llama3.2
echo "Model downloaded successfully!"

# Wait for server process to keep container running
wait $SERVER_PID
