#!/usr/bin/python3
"""Blue sky."""
import sys
import pygame

def run_game():
    """Start game."""
    pygame.init()
    screen = pygame.display.set_mode((1200, 700))
    pygame.display.set_caption("Blue sky")
    image = pygame.image.load('images/donkey_kong.png')

    image_rect = image.get_rect()
    screen_rect = screen.get_rect()

    image_rect.centerx = screen_rect.centerx
    image_rect.bottom = screen_rect.bottom

    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                sys.exit()
            screen.fill((0, 0, 255))
            screen.blit(image, image_rect)
            pygame.display.flip()

run_game()
