#!/bin/bash

cd authorization-server

./mvnw clean package

cd ..
cd resource-server

./mvnw clean package

docker-compose up -d --build
