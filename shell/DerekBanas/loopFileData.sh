#!/bin/bash

while read avg rbis hrs; do
  printf "Avg: ${avg}\nRBIs: ${rbis}\nHRs: ${hrs}\n"
done < barry_bonds.txt

