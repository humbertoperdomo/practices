#import "Memory.h"

@implementation Memory
@synthesize zAryOfLines;
@synthesize path;

- (id) initWithPath:(NSString *) path_ {
	self = [super init];
	if (self) {
		path = path_;
	}
	return self;
}

- (id) initWithArray:(NSArray *) zAryOfLines_ {
	self = [super init];
	if (self){
		zAryOfLines = zAryOfLines_;
	}
	return self;
}

- (NSString *) getInstruction:(int) line {

	return (NSString *)[zAryOfLines objectAtIndex:line > 0 ? line - 1 : line];
}

- (void) writeUsingObjectiveC {
   NSString * zStr = [[NSString alloc]init];
   NSInteger i;
   for (i = 0; i < 5; i++) {
      zStr = [zStr stringByAppendingFormat:@"i=%d\n",i];
   }
   
   [zStr writeToFile:path 
          atomically:YES 
            encoding:NSASCIIStringEncoding error:NULL]; 
}

- (void) readUsingObjectiveC {
   NSString * zStr = 
              [NSString stringWithContentsOfFile:path 
                                        encoding:NSASCIIStringEncoding 
                                           error:NULL];
   
   // extract the data line by line
   zAryOfLines = [zStr componentsSeparatedByString:@"\n"];
   if([zAryOfLines count] == 0) {
      NSLog(@"zAryOfLines count = 0");
      return;
   }
}

- (void) writeUsingC {
   FILE *fp = fopen([path UTF8String],"w");
   NSInteger i;
   for(i = 0;i < 5; i++){
      fprintf(fp,"i=%d\n",(int)i);
   }
   fclose(fp);
} 

- (void) readUsingC {
   FILE *fp = fopen([path UTF8String],"r");
   int k;
   char myStr[20];
   NSInteger i;
   for(i = 0;i < 5; i++){
      fscanf(fp,"%s %d\n",myStr,&k);
      NSLog(@"i=%d,%s k=%d",i,myStr,k);
   }
   fclose(fp);
   
}

- (void) printMemory {
   int count = 1;
   for (NSString * zStrLine in zAryOfLines) {
      /*
      NSInteger zInt;
      NSString * zStr2;
      NSScanner* zScanner = [NSScanner scannerWithString:zStrLine];
      [zScanner scanString:@"i=" intoString:&zStr2];
      [zScanner scanInteger:&zInt];
      NSLog(@"zStr2=%@, zInt=%d", zStr2,zInt);
      */
      NSLog(@"%d. %@",count++, zStrLine);
   }

}
@end