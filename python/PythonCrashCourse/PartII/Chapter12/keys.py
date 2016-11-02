#!/usr/bin/python3
"""Keys."""
import sys
import pygame

def run_game():
    """Start game."""
    pygame.init()
    screen = pygame.display.set_mode((1200,700))
    pygame.display.set_caption("Keys")

    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                sys.exit()
            elif event.type == pygame.KEYDOWN:
                print(event.key)
        screen.fill((255, 255, 255))
        pygame.display.flip()

run_game()
