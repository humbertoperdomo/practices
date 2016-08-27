#!/usr/bin/python3
# square_spiral_3.py
"""Draws a square spiral
"""
import turtle
import random

COLORS = ["red", "yellow", "blue", "green"]

t = turtle.Pen()
#turtle.colormode(255)
turtle.bgcolor("black")
for x in range(100):
    t.forward(x)
    t.left(91)
    t.pencolor(COLORS[x%len(COLORS)])
    #t.pencolor(random.randint(0, 254), random.randint(0, 254), random.randint(0, 254))
