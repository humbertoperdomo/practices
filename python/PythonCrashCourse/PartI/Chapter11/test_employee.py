#!/usr/bin/python3
"""Test Employee class."""
import unittest
from employee import Employee

class TestEmployee(unittest.TestCase):
    """TestEmployee class."""

    def setUp(self):
        """Generate employee instance to be used in tests."""
        self.employee = Employee('humberto', 'perdomo', 60000)

    def test_give_defaul_rise(self):
        """Test default rise."""
        self.employee.give_raise()
        self.assertEqual(self.employee.annual_salary, 65000)

    def test_give_custom_raise(self):
        """Test custom raise."""
        self.employee.give_raise(7000)
        self.assertEqual(self.employee.annual_salary, 67000)

unittest.main()
