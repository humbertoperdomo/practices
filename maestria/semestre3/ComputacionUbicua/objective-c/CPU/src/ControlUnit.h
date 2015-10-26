#import <Foundation/Foundation.h>
#import "Registers.h"
#import "Memory.h"
#import "ArithLogicUnit.h"

@interface ControlUnit : NSObject {
  Registers *registers;
  Memory *memory;
}
@property (nonatomic, retain) Registers *registers;
@property (retain) Memory *memory;
- (id) initWithRegisters:(Registers *) registers_;
- (void) execute;
- (NSString *) fetchInstruction:(int) memoryAddress;
@end