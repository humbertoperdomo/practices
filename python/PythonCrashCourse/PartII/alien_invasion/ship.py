#!/usr/bin/python3
"""Ship class."""
import pygame

class Ship():
    """Model of a ship."""

    def __init__(self, ai_settings, screen):
        """Initialize the ship and set its starting position."""
        self._screen = screen
        self._ai_settings = ai_settings

        # Load the ship image and get its rect.
        self._image = pygame.image.load('images/ship.bmp')
        self._rect = self._image.get_rect()
        self._screen_rect = screen.get_rect()

        # Start each new ship at the bottom center of the screen.
        self._rect.centerx = self._screen_rect.centerx
        self._rect.bottom = self._screen_rect.bottom

        # Store a decimal value for the ship's center.
        self._center = float(self._rect.centerx)

        # Movement flag
        self._moving_right = False
        self._moving_left = False

    def update(self):
        """Update the ship's position based on the movement flag."""
        # Update the ship's center value, not the rect.
        if self.moving_right and self.rect.right < self.screen_rect.right:
            self.center += self.ai_settings.ship_speed_factor
        if self.moving_left and self.rect.left > 0:
            self.center -= self.ai_settings.ship_speed_factor

        # Update rect object from self.center.
        self.rect.centerx = self.center

    def blitme(self):
        """Draw the ship at its current location."""
        self.screen.blit(self._image, self._rect)

    @property
    def screen(self):
        """Screen getter."""
        return self._screen

    @property
    def ai_settings(self):
        """Ai settings getter."""
        return self._ai_settings

    @property
    def rect(self):
        """Rect getter."""
        return self._rect

    @property
    def screen_rect(self):
        """Screen rect getter."""
        return self._screen_rect

    @property
    def center(self):
        """Center getter."""
        return self._center

    @center.setter
    def center(self, value):
        """Moving right setter."""
        self._center = value

    @property
    def moving_right(self):
        """Moving right getter."""
        return self._moving_right

    @moving_right.setter
    def moving_right(self, value):
        """Moving right setter."""
        self._moving_right = value

    @property
    def moving_left(self):
        """Moving left getter."""
        return self._moving_left

    @moving_left.setter
    def moving_left(self, value):
        """Moving left setter."""
        self._moving_left = value
