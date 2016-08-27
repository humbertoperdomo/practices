#!/usr/bin/python3
# circle_spiral_1.py
"""Draws a circle spiral
"""
import turtle

t = turtle.Pen()
for x in range(100):
    t.circle(x)
    t.left(90)
