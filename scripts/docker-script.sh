#!/bin/bash

  DOCKER_IMAGE_NAME=mytamla

  DOCKER_CONTAINER_NAME=goorm

  docker build -t ${DOCKER_IMAGE_NAME} . #  프로젝트 루트 경로에 대한 상대경로

  docker run -d -p --name ${DOCKER_CONTAINER_NAME} ${DOCKER_IMAGE_NAME}