#!/bin/bash

set -e

IMAGE_NAME="avenga-bookstore-api-tests"
JOB_NAME="bookstore-api-tests"

echo "Building Docker image..."
docker build -t $IMAGE_NAME:latest .

echo "Deleting old Kubernetes job if exists..."
kubectl delete job $JOB_NAME --ignore-not-found=true

echo "Applying Kubernetes job..."
kubectl apply -f k8s/api-tests-job.yaml

echo "Waiting for pod creation..."
sleep 5

POD_NAME=$(kubectl get pods --selector=job-name=$JOB_NAME --output=jsonpath="{.items[0].metadata.name}")

echo "Pod created: $POD_NAME"

echo "Waiting for test execution..."
kubectl wait --for=condition=complete job/$JOB_NAME --timeout=120s

echo "Showing logs..."
kubectl logs $POD_NAME

echo "Done."