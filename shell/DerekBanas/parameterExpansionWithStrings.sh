#!/bin/bash

rand_str="A random string"

echo "String length: ${#rand_str}"
echo "${rand_str:3}"
echo "${rand_str:4:7}"
echo "${rand_str#*A }"
