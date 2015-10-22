#import "Registers.h"

@implementation Registers
@synthesize pc;
@synthesize ir;
@synthesize mar;
@synthesize mbr;
@synthesize al;
@synthesize ah;
@synthesize bl;
@synthesize bh;

- (id) initWithFirstInstructionLocation:(int) memoryLocation {
  self = [super init];
  if (self) {
    pc = memoryLocation;
  }
  return self;
}

- (void) transferPCToMAR {
  mar = pc;
}
@end