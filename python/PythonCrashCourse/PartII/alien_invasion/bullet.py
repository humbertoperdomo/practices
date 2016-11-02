#!/usr/bin/python3
"""Bullet."""
import pygame
from pygame.sprite import Sprite

class Bullet(Sprite):
    """A class to manage bullets fired from the ship."""

    def __init__(self, ai_settings, screen, ship):
        """Create a bullet object at the ship's current position."""
        super(Bullet, self).__init__()
        self._screen = screen

        # Create a bullet rect at (0, 0) and then set correct position.
        self._rect = pygame.Rect(0, 0, ai_settings.bullet_width,
                                 ai_settings.bullet_height)
        self._rect.centerx = ship.rect.centerx
        self._rect.top = ship.rect.top

        # Store the bullet's position as a decima value.
        self._y = float(self._rect.y)

        self._color = ai_settings.bullet_color
        self._speed_factor = ai_settings.bullet_speed_factor

    def update(self):
        """Move the bullet up the screen."""
        # Update the decimal position of the bullet.
        self._y -= self.speed_factor
        # Update the rect position.
        self.rect.y = self.y

    def draw_bullet(self):
        """Draw the bullet to the screen."""
        pygame.draw.rect(self.screen, self.color, self.rect)

    @property
    def screen(self):
        """Screen getter."""
        return self._screen

    @property
    def rect(self):
        """Rect getter."""
        return self._rect

    @property
    def y(self):
        """Y getter."""
        return self._y

    @property
    def color(self):
        """Color getter."""
        return self._color

    @property
    def speed_factor(self):
        """Speed factor getter."""
        return self._speed_factor
