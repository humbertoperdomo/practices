#!/usr/bin/python3
"""Settings class."""

class Settings():
    """A class to store all settings for Alien Invasion."""

    def __init__(self):
        """Initialize the game's settings."""
        # Screen settings
        self._screen_width = 1200
        self._screen_height = 700
        self._bg_color = (230, 230, 230)

        # Ship settings
        self._ship_speed_factor = 1.5

        # Bullet settings
        self._bullet_speed_factor = 1
        self._bullet_width = 3
        self._bullet_height = 15
        self._bullet_color = 60, 60, 60
        self._bullets_allowed = 3

    @property
    def screen_width(self):
        """Screen width getter."""
        return self._screen_width

    @screen_width.setter
    def screen_width(self, scree_width):
        self._screen_width = scree_width

    @property
    def screen_height(self):
        """Screen height getter."""
        return self._screen_height

    @screen_height.setter
    def screen_height(self, screen_height):
        self._screen_height = screen_height

    @property
    def bg_color(self):
        """Background color getter."""
        return self._bg_color

    @bg_color.setter
    def bg_color(self, bg_color):
        self._bg_color = bg_color

    @property
    def ship_speed_factor(self):
        """Ship speed factor getter."""
        return self._ship_speed_factor

    @ship_speed_factor.setter
    def ship_speed_factor(self, ship_speed_factor):
        self._ship_speed_factor = ship_speed_factor

    @property
    def bullet_speed_factor(self):
        """Bullet speed factor getter."""
        return self._bullet_speed_factor

    @property
    def bullet_width(self):
        """Bullet width getter."""
        return self._bullet_width

    @property
    def bullet_height(self):
        """Bullet height getter."""
        return self._bullet_height

    @property
    def bullet_color(self):
        """Bullet color getter."""
        return self._bullet_color

    @property
    def bullets_allowed(self):
        """Bullets allowed getter."""
        return self._bullets_allowed
