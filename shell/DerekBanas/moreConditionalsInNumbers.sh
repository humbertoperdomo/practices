#!/bin/bash
read -p "Enter a number: " num

if ((num == 10)); then
  echo "Your number equals 10"
fi

if ((num >= 10)); then
  echo "It is greater than 10"
else
  echo "It is less than 10"
fi

if (( ((num % 2)) == 0)); then
  echo "It is even"
fi

if (( ((num > 0)) && ((num < 11)) )); then
  echo "$num is between 1 and 10"
fi
