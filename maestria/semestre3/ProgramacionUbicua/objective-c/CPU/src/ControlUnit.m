#import "ControlUnit.h"

@implementation ControlUnit
@synthesize registers;
@synthesize memory;

-(id) initWithRegisters:(Registers *) registers_ {
  self = [super init];
  if (self) {
    registers = registers_;
  }
  return self;
}

- (void) execute {
    [memory printMemory];
	[registers transferPCToMAR];
    NSLog(@"MAR: %d\n", registers.mar);
    registers.mbr = [self fetchInstruction:registers.mar];
    
	NSLog(@"First instruction: %@", registers.mbr);
}

- (NSString *) fetchInstruction:(int) memoryAddress {
	return [memory getInstruction:memoryAddress];
}
@end