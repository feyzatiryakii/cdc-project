FROM python:3.11-slim
WORKDIR /app
COPY dataGenerator.py .
RUN pip install --no-cache-dir faker mysql-connector-python
CMD ["python", "dataGenerator.py"]