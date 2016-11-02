#!/usr/bin/python3
"""Alien class"""
import pygame
from pygame.sprite import Sprite

class Alien(Sprite):
    """A class to represent a single alien in the fleet."""

    def __init__(self, ai_settings, screen):
        """Initialize the alien and set its starting position."""
        super(Alien, self).__init__()
        self._screen = screen
        self._ai_settings = ai_settings

        # Load the alien image and set its rect attribute.
        self._image = pygame.image.load('images/alien.bmp')
        self._rect = self.image.get_rect()

        # Start each new alien near the top left of the screen.
        self.rect.x = self.rect.width
        self.rect.y = self.rect.height

        # Store the alien's exact position.
        self._x = float(self.rect.x)

    def blitme(self):
        """Draw the alien at its current location."""
        self.screen.blit(self.image, self.rect)

    @property
    def screen(self):
        """Screen getter."""
        return self._screen

    @property
    def ai_settings(self):
        """AI settings getter."""
        return self._ai_settings

    @property
    def image(self):
        """Image getter."""
        return self._image

    @property
    def rect(self):
        """Rect getter."""
        return self._rect

    @property
    def x(self):
        """X getter."""
        return self._x

    @x.setter
    def x(self, x):
        self._x = x
