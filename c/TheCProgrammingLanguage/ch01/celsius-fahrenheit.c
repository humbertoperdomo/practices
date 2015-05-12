#include <stdio.h>

/**
 * Print Celsius-Fahrenheit table
 * for celsius = 0, 20, ..., 300
 */
float celsiusToFahrenheit(float);

main() {
  float fahr, celsius;
  float lower, upper, step;

  lower = 0;    // lower limit of temperature scale
  upper = 300;  // upper limit
  step = 20;    // step size
  
  printf("Celsius\tFahrenheit\n");
  celsius = lower;
  while(celsius <= upper) {
    fahr = celsiusToFahrenheit(celsius);
    printf("%3.0f\t%6.1f\n", celsius, fahr);
    celsius = celsius + step;
  }
}

float celsiusToFahrenheit(float c) {
  return ((9.0/5.0) * c) + 32.0;
}
