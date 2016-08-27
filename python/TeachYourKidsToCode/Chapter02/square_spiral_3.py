#!/usr/bin/python3
# square_spiral_3.py
"""Draws a square spiral
"""
import turtle

t = turtle.Pen()
t.pencolor("red")
for x in range(100):
    t.forward(x)
    t.left(91)
