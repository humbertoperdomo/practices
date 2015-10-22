#import <Foundation/Foundation.h>

@interface Registers : NSObject {
  int pc; // Program Counter
  NSString *ir; // Instruction Register
  int mar; // Memory Address Register
  NSString *mbr; // Memory Buffer Register
  int al; 
  int ah;
  int bl;
  int bh;
}
@property (nonatomic, readwrite) int pc;
@property (nonatomic, retain) NSString *ir;
@property (nonatomic, readwrite) int mar;
@property (nonatomic, retain) NSString *mbr;
@property (nonatomic, readwrite) int al;
@property (nonatomic, readwrite) int ah;
@property (nonatomic, readwrite) int bl;
@property (nonatomic, readwrite) int bh;

- (id) initWithFirstInstructionLocation:(int) memoryLocation;
- (void) transferPCToMAR;
- (void) storeIntructionInMBR:(NSString *) instruction;
@end
