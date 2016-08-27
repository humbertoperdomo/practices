#!/usr/bin/python3
# square_spiral_1.py
"""Draws a square spiral
"""
import turtle
t = turtle.Pen()
for x in range(100):
    t.forward(x)
    t.left(90)
