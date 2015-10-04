#include <iostream>
#include "Sales_item.h" // Class that holds transaction

int main() {
  Sales_item total;

  if (std::cin >> total ) {
    Sales_item trans;
    int count = 1;
    while (std::cin >> trans) {
      if (total.isbn() == trans.isbn()) {
        total += trans;
        count++;
      } else {
        std::cout << total << std::endl;
        std::cout << "\t" << count << " transactions of " << total.isbn() << std::endl;
        total = trans;
        count = 1;
      }
    }
    std::cout << total << std::endl;
    std::cout << "\t" << count << " transactions of " << total.isbn() << std::endl;
  } else {
    std::cout << "No data?!" << std::endl;
    return -1;
  }
  return 0;
}
