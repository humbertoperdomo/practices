#!/usr/bin/python3
# color_circle_spiral.py
"""Draws a color circle spiral
"""
import turtle

COLORS = ["red", "yellow", "blue", "green"]

T = turtle.Pen()
turtle.bgcolor("black")
for x in range(100):
    T.pencolor(COLORS[x%4])
    T.circle(x)
    T.left(91)
