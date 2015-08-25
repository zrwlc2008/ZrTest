package test.swf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
 
public class SWFHeader {
 
    public static final int HEADER_BUFFER_SIZE = 512;
     
    public static SWFHeader load(File file) throws IOException, IllegalAccessException{
        FileChannel fc = new FileInputStream(file).getChannel();
        ByteBuffer buffer = fc.map(MapMode.READ_ONLY, 0L, HEADER_BUFFER_SIZE);
        fc.close();
        SWFHeader header = new SWFHeader();
        header.format = new String(new char[]{(char)buffer.get(),(char)buffer.get(),(char)buffer.get()});
        header.version = buffer.get()&0xFF;
        header.uncompressedFileLength = buffer.get()&0xFF | ((buffer.get()&0xFF)<<8) | ((buffer.get()&0xFF)<<16) | ((buffer.get()&0xFF)<<32);
        if(header.format.charAt(0)=='C'){
            Inflater decompr = new Inflater();
            byte[] array = new byte[HEADER_BUFFER_SIZE-8];
            buffer.get(array);
            decompr.setInput(array);
            try {
                decompr.inflate(array);
                buffer = ByteBuffer.wrap(array);
            } catch (DataFormatException e) {
                throw new IllegalAccessException("Unzip data error : "+e.getMessage());
            }
        }
        if(header.format.charAt(0)=='Z'){
            throw new IllegalAccessException("not support LZMA compressed data");
        }
        header.ctrlCode = buffer.get();
        switch (header.ctrlCode) {
        case 0x50:
            header.height = ((buffer.get()&0xFF)<<8) | buffer.get() & 0xFF ;
            header.width  = ( ((buffer.get()&0x0F)<<16) | ((buffer.get()&0xFF)<<8) | (buffer.get() & 0xFF) ) >>> 4;
            header.height /= 10; 
            header.width /= 10; 
            break;
        case 0x58:
            header.height  = ( ((buffer.get()&0x0F)<<16) | ((buffer.get()&0xFF)<<8) | (buffer.get() & 0xFF) ) >>> 4;
            header.width = ((buffer.get()&0xFF)<<8) | buffer.get() & 0xFF ;
            header.height /= 40; 
            header.width /= 10; 
            break;
        case 0x60:
            header.height  = ( ((buffer.get()&0x0F)<<16) | ((buffer.get()&0xFF)<<8) | (buffer.get() & 0xFF) ) >>> 4;
            header.width  = ( ((buffer.get()&0x0F)<<16) | ((buffer.get()&0xFF)<<8) | (buffer.get() & 0xFF) ) >>> 4;
            header.height /= 10; 
            header.width /= 10; 
            break;
        case 0x68:
            buffer.get();
            header.height = ((buffer.get()&0xFF)<<8) | buffer.get() & 0xFF ;
            buffer.get();
            header.width = ((buffer.get()&0xFF)<<8) | buffer.get() & 0xFF ;
            header.height /= 40; 
            header.width /= 10;
            break;
        case 0x70:
            buffer.get();
            header.height = ((buffer.get()&0xFF)<<8) | buffer.get() & 0xFF ;
            buffer.get();
            header.width  = ( ((buffer.get()&0x0F)<<16) | ((buffer.get()&0xFF)<<8) | (buffer.get() & 0xFF) ) >>> 4;
            header.height /= 10; 
            header.width /= 10; 
            break;
        case 0x78:
            buffer.get();
            header.height  = ( ((buffer.get()&0x0F)<<16) | ((buffer.get()&0xFF)<<8) | (buffer.get() & 0xFF) ) >>> 4;
            buffer.get();
            header.width = ((buffer.get()&0xFF)<<8) | buffer.get() & 0xFF ;
            header.height /= 40; 
            header.width /= 10;
            break;
        case (byte)0x80:
            buffer.get();
            header.height  = ( ((buffer.get()&0x0F)<<16) | ((buffer.get()&0xFF)<<8) | (buffer.get() & 0xFF) ) >>> 4;
            buffer.get();
            header.width  = ( ((buffer.get()&0x0F)<<16) | ((buffer.get()&0xFF)<<8) | (buffer.get() & 0xFF) ) >>> 4;
            header.height /= 10; 
            header.width /= 10; 
            break;
        case (byte)0x88:
            buffer.get();
            header.height = ((buffer.get()&0xFF)<<16) | ((buffer.get()&0xFF)<<8) | buffer.get() & 0xFF ;
            buffer.get();buffer.get();
            header.width = ((buffer.get()&0xFF)<<8) | buffer.get() & 0xFF ;
            header.height /= 40; 
            header.width /= 10;
            break;
        default:
            break;
        }
        return header;
    }
     
    private String format;
    private int version;
    private long uncompressedFileLength;
    private byte ctrlCode;
    private int width;
    private int height;
     
    @Override
    public String toString() {
        final char[] HEXs = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        StringBuilder builder = new StringBuilder();
        builder.append("{format:\"").append(format).append("\",version:").append(version);
        builder.append(",uncompressedFileLength:").append(uncompressedFileLength);
        builder.append(",ctrlCode:0x").append(HEXs[((ctrlCode&0xF0)>>4)]).append(HEXs[(ctrlCode&0x0F)]);
        builder.append(",width:").append(width).append(",height:").append(height).append("}");
        return builder.toString();
    }
     
    public String getFormat() {
        return format;
    }
 
    public int getVersion() {
        return version;
    }
 
    public long getUncompressedFileLength() {
        return uncompressedFileLength;
    }
 
    public byte getCtrlCode() {
        return ctrlCode;
    }
 
    public int getWidth() {
        return width;
    }
 
    public int getHeight() {
        return height;
    }
 
    public static void main(String[] args) throws IOException, IllegalAccessException {
        File file = new File("D:\\workspace\\ZrTest\\src\\test\\swf\\flash3870.swf");
        System.out.println(file.length());
        System.out.println(SWFHeader.load(file));
 
    }
}
