#!/bin/bash

read -p "Validate date: " date

pat="^[0-9]{8}$"

if [[ $date =~ $pat ]]; then
  echo "$date is valid"
else
  echo "$date not valid"
fi
