#!/bin/bash

fav_nums=(3.14 2.718 0.57721 4.6692)

echo "Pi: ${fav_nums[0]}"

fav_nums[4]=1.618

echo "GR: ${fav_nums[4]}"

fav_nums+=(1 7)

for i in ${fav_nums[*]}; do
  echo $i
done

for i in ${fav_nums[@]}; do
  echo $i
done

echo "Array length: ${#fav_nums[@]}"

echo "Index 3 length: ${#fav_nums[3]}"

sorted_nums=($(for i in "${fav_nums[@]}"; do
  echo $i;
done | sort))

for i in ${sorted_nums[*]}; do
  echo $i
done

unset 'sorted_nums[1]'
unset sorted_nums
