#!/bin/bash

set -e

IMAGE_NAME="avenga-bookstore-api-tests"

echo "Cleaning old Allure results and report..."
rm -rf target/allure-results
rm -rf target/allure-report

echo "Building Docker image..."
docker build --no-cache -t "$IMAGE_NAME" .

echo "Running API tests inside Docker..."
MSYS_NO_PATHCONV=1 docker run --rm -v "$(pwd)/target:/app/target" "$IMAGE_NAME"

echo "Adding Allure environment info..."
cp src/test/resources/environment.properties target/allure-results/environment.properties

echo "Generating Allure report..."
allure generate target/allure-results --clean --single-file -o target/allure-report

echo "Opening Allure report in browser..."
REPORT_PATH="$(cygpath -w "$(pwd)/target/allure-report/index.html")"
powershell.exe -NoProfile -Command "Start-Process '$REPORT_PATH'"

echo "Done."