#import <Foundation/Foundation.h>
#import "Registers.h"
#import "ControlUnit.h"
#import "Memory.h"

int main (int argc, const char * argv[]) {
  NSAutoreleasePool * pool = [[NSAutoreleasePool alloc]init];
  if (argc == 2) { // Validate if program file path is provided
    NSString *pathFileMemoryProgram = [NSString stringWithUTF8String:argv[1]];

    NSFileManager *fileManager = [NSFileManager defaultManager];

    if ([fileManager fileExistsAtPath:pathFileMemoryProgram]) { // Validete if program file exists
      Memory *memory = [[Memory alloc]initWithPath:pathFileMemoryProgram];
      [memory readUsingObjectiveC];

      if ([memory.zAryOfLines count] > 0) {
        Registers *registers = [[Registers alloc]initWithFirstInstructionLocation:1];
        ControlUnit *controlUnit = [[ControlUnit alloc]initWithRegisters:registers];
        controlUnit.memory = memory;

        [controlUnit execute];
      }
    } else {
      NSLog(@"File \"%@\" does not exist.", pathFileMemoryProgram);
    }
  } else if (argc > 2) {
    NSLog(@"Too many arguments supplied.");
  } else {
    NSLog(@"One argument expected.");
  }
  [pool drain];
  return 0;
}
