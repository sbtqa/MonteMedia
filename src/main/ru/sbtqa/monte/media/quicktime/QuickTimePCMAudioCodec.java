/* @(#)PCMAudioCodec.java
 * Copyright © 2011 Werner Randelshofer, Switzerland. 
 * You may only use this software in accordance with the license terms.
 */
package ru.sbtqa.monte.media.quicktime;

import ru.sbtqa.monte.media.audio.*;
import ru.sbtqa.monte.media.Format;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashSet;
import static ru.sbtqa.monte.media.FormatKeys.*;
import static ru.sbtqa.monte.media.AudioFormatKeys.*;

/**
 * {@code PCMAudioCodec} performs sign conversion, endian conversion and
 * quantization conversion of PCM audio data.
 * 
 * Does not perform sampling rate conversion or channel conversion.
 *
 * @author Werner Randelshofer
 * @version $Id: QuickTimePCMAudioCodec.java 364 2016-11-09 19:54:25Z werner $
 */
public class QuickTimePCMAudioCodec extends PCMAudioCodec {

    private final static HashSet<String> signedEncodings = new HashSet<String>(
            Arrays.asList(new String[]{
                ENCODING_PCM_SIGNED, ENCODING_QUICKTIME_TWOS_PCM, ENCODING_QUICKTIME_SOWT_PCM,
                ENCODING_QUICKTIME_IN24_PCM, ENCODING_QUICKTIME_IN32_PCM,}));
    private final static HashSet<String> unsignedEncodings = new HashSet<String>(
            Arrays.asList(new String[]{
                ENCODING_PCM_UNSIGNED, ENCODING_QUICKTIME_RAW_PCM}));

    public QuickTimePCMAudioCodec() {
        super(new Format[]{
                    new Format(MediaTypeKey, MediaType.AUDIO,//
                    MimeTypeKey, MIME_JAVA,//
                    EncodingKey, ENCODING_PCM_SIGNED,
                    SignedKey, true),//
                    new Format(MediaTypeKey, MediaType.AUDIO,//
                    MimeTypeKey, MIME_JAVA,//
                    EncodingKey, ENCODING_PCM_UNSIGNED,
                    SignedKey, false),//
                    //
                    // 8-bit unsigned has "raw " encoding regardless of endian
                    new Format(MediaTypeKey, MediaType.AUDIO,//
                    EncodingKey, ENCODING_QUICKTIME_RAW_PCM,//
                    MimeTypeKey, MIME_QUICKTIME,//
                    SignedKey, false, SampleSizeInBitsKey, 8),//
                    //
                    // 8-bit signed has "sowt" encoding for little endian
                    new Format(MediaTypeKey, MediaType.AUDIO, //
                    EncodingKey, ENCODING_QUICKTIME_SOWT_PCM,//
                    MimeTypeKey, MIME_QUICKTIME,//
                    ByteOrderKey, ByteOrder.LITTLE_ENDIAN,
                    SignedKey, true, SampleSizeInBitsKey, 8),//
                    //
                    // 8-bit signed has "twos" encoding for big endian
                    new Format(MediaTypeKey, MediaType.AUDIO, //
                    EncodingKey, ENCODING_QUICKTIME_TWOS_PCM,//
                    MimeTypeKey, MIME_QUICKTIME,//
                    ByteOrderKey, ByteOrder.BIG_ENDIAN,
                    SignedKey, true, SampleSizeInBitsKey, 8),//

                    // 16-bit, signed, little endian
                    new Format(MediaTypeKey, MediaType.AUDIO, 
                    EncodingKey, ENCODING_QUICKTIME_SOWT_PCM,//
                    MimeTypeKey, MIME_QUICKTIME,//
                    ByteOrderKey, ByteOrder.LITTLE_ENDIAN,
                    SignedKey, true,
                    SampleSizeInBitsKey, 16),//
                    //
                    // 16-bit, signed, big endian
                    new Format(MediaTypeKey, MediaType.AUDIO, //
                    EncodingKey, ENCODING_QUICKTIME_TWOS_PCM,//
                    MimeTypeKey, MIME_QUICKTIME,//
                    ByteOrderKey, ByteOrder.BIG_ENDIAN,
                    SignedKey, true,
                    SampleSizeInBitsKey, 16),//
                    //
                    new Format(MediaTypeKey, MediaType.AUDIO, //
                    EncodingKey, ENCODING_QUICKTIME_IN24_PCM,//
                    MimeTypeKey, MIME_QUICKTIME,//
                    ByteOrderKey, ByteOrder.BIG_ENDIAN,
                    SignedKey, true, SampleSizeInBitsKey, 24),//
                    //
                    new Format(MediaTypeKey, MediaType.AUDIO,//
                    EncodingKey, ENCODING_QUICKTIME_IN32_PCM,
                    MimeTypeKey, MIME_QUICKTIME,//
                    ByteOrderKey, ByteOrder.BIG_ENDIAN,
                    SignedKey, true, SampleSizeInBitsKey, 32),//
                });
        name="QuickTime PCM Codec";
    }
}
