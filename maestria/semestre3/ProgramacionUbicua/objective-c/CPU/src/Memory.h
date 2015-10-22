#import <Foundation/Foundation.h>

@interface Memory : NSObject {
	NSArray *zAryOfLines;
	NSString *path;
}
@property (retain) NSArray *zAryOfLines;
@property (retain) NSString *path;
- (id) initWithPath:(NSString *) path_;
- (id) initWithArray:(NSArray *) zAryOfLines_;
- (NSString *) getInstruction:(int) line;
- (void) writeUsingObjectiveC;
- (void) readUsingObjectiveC;
- (void) writeUsingC;
- (void) readUsingC;
- (void) printMemory;
@end