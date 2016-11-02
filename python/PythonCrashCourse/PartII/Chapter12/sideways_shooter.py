#!/usr/bin/python3
"""Rocket."""
import sys
import pygame
from pygame.sprite import Group
from pygame.sprite import Sprite

def run_game():
    """Start game."""
    # initialize pygame, settings and create a screen object
    pygame.init()
    screen = pygame.display.set_mode((1200, 700))
    pygame.display.set_caption("Sideways shooter")
    rocket_speed_factor = 1.5
    # Bullet settings
    bullet_speed_factor = 1
    bullet_width = 15
    bullet_height = 3
    bullet_color = 60, 60, 60
    bullets_allowed = 3

    # Load the ship image and egt its rect.
    image = pygame.image.load('images/ship.bmp')
    image_rect = image.get_rect()
    screen_rect = screen.get_rect()

    # Start each new rocket at the bottom center of the screen.
    image_rect.left = 0
    image_rect.centery = screen_rect.centery

    # Store a decimal value for the rocket's bottom.
    middle = float(image_rect.centery)

    # Movement flags
    moving_up = False
    moving_down = False
    # Make a group to store bullets in.
    bullets = Group()

    while True:
        # check events
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                sys.exit()
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_UP:
                    moving_up = True
                elif event.key == pygame.K_DOWN:
                    moving_down = True
                elif event.key == pygame.K_SPACE:
                    # Create a new bullet and add it to the bullets group.
                    if len(bullets) < bullets_allowed:
                        new_bullet = Bullet(screen, image_rect)
                        bullets.add(new_bullet)
            elif event.type == pygame.KEYUP:
                if event.key == pygame.K_UP:
                    moving_up = False
                elif event.key == pygame.K_DOWN:
                    moving_down = False

        # update image
        if moving_up and image_rect.top > 0:
            middle -= rocket_speed_factor
        if moving_down and image_rect.bottom < screen_rect.bottom:
            middle += rocket_speed_factor
        # update rect object from bottom
        image_rect.centery = middle


        # Update bullet positions.
        bullets.update()

        # Get rid of bullets that have disappeared.
        for bullet in bullets.copy():
            if bullet.rect.left >= screen_rect.right:
                bullets.remove(bullet)
            #print(len(bullets))

        # Redraw the screen during each pass through the loop.
        screen.fill((230, 230, 230))
        # Redraw all bullets behind ship and aliens.
        for bullet in bullets.sprites():
            bullet.draw_bullet()

        # Draw the ship at its current location.
        screen.blit(image, image_rect)
        # Make the most recently draw screen visible.
        pygame.display.flip()


class Bullet(Sprite):
    """A class to manage bullets fired from the ship."""

    def __init__(self, screen, ship):
        """Create a bullet object at the ship's current position."""
        super(Bullet, self).__init__()
        self.screen = screen

        # Create a bullet rect at (0, 0) and then set correct position.
        self.rect = pygame.Rect(0, 0, 15, 3)
        self.rect.centery = ship.centery
        self.rect.x = ship.right

        # Store the bullet's position as a decima value.
        self.x = float(self.rect.x)

        self.color = 60, 60, 60
        self.speed_factor = 1

    def update(self):
        """Move the bullet up the screen."""
        # Update the decimal position of the bullet.
        self.x += self.speed_factor
        # Update the rect position.
        self.rect.x = self.x

    def draw_bullet(self):
        """Draw the bullet to the screen."""
        pygame.draw.rect(self.screen, self.color, self.rect)

run_game()
