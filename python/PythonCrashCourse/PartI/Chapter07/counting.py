#!/usr/bin/python3

MAX = 10

# The while loop in action
# Using continue in a loop
current_number = 0
while current_number < MAX:
    current_number += 1
    if current_number % 2 == 0:
        continue
    
    print(current_number)


