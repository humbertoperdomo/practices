#!/usr/bin/python3
"""Rocket."""
import sys
import pygame

def run_game():
    """Start game."""
    # initialize pygame, settings and create a screen object
    pygame.init()
    screen = pygame.display.set_mode((1200, 700))
    pygame.display.set_caption("Blue sky")
    rocket_speed_factor = 1.5

    # Load the ship image and egt its rect.
    image = pygame.image.load('images/rocket.png')
    image_rect = image.get_rect()
    screen_rect = screen.get_rect()

    # Start each new rocket at the bottom center of the screen.
    image_rect.centerx = screen_rect.centerx
    image_rect.bottom = screen_rect.bottom

    # Store a decimal value for the rocket's center.
    center = float(image_rect.centerx)
    # Store a decimal value for the rocket's bottom.
    middle = float(image_rect.centery)

    # Movement flags
    moving_right = False
    moving_left = False
    moving_up = False
    moving_down = False

    while True:
        # check events
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                sys.exit()
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_RIGHT:
                    moving_right = True
                elif event.key == pygame.K_LEFT:
                    moving_left = True
                elif event.key == pygame.K_UP:
                    moving_up = True
                elif event.key == pygame.K_DOWN:
                    moving_down = True
            elif event.type == pygame.KEYUP:
                if event.key == pygame.K_RIGHT:
                    moving_right = False
                elif event.key == pygame.K_LEFT:
                    moving_left = False
                elif event.key == pygame.K_UP:
                    moving_up = False
                elif event.key == pygame.K_DOWN:
                    moving_down = False

        # update image
        if moving_right and image_rect.right < screen_rect.right:
            center += rocket_speed_factor
        if moving_left and image_rect.left > 0:
            center -= rocket_speed_factor
        if moving_up and image_rect.top > 0:
            middle -= rocket_speed_factor
        if moving_down and image_rect.bottom < screen_rect.bottom:
            middle += rocket_speed_factor
        # update rect object from center
        image_rect.centerx = center
        # update rect object from bottom
        image_rect.centery = middle

        # Redraw the screen during each pass through the loop.
        screen.fill((0, 0, 0))
        # Draw the ship at its current location.
        screen.blit(image, image_rect)
        # Make the most recently draw screen visible.
        pygame.display.flip()

run_game()
