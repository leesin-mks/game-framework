/*
 * Copyright 2016-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.game.pb;

public final class CenterMsgProto {
  private CenterMsgProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CSForwardMsgOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 fromServerID = 1;
    /**
     * <code>required int32 fromServerID = 1;</code>
     *
     * <pre>
     * 转发消息始服务器
     * </pre>
     */
    boolean hasFromServerID();
    /**
     * <code>required int32 fromServerID = 1;</code>
     *
     * <pre>
     * 转发消息始服务器
     * </pre>
     */
    int getFromServerID();

    // required int32 toServerID = 2;
    /**
     * <code>required int32 toServerID = 2;</code>
     *
     * <pre>
     * 转发消息终服务器
     * </pre>
     */
    boolean hasToServerID();
    /**
     * <code>required int32 toServerID = 2;</code>
     *
     * <pre>
     * 转发消息终服务器
     * </pre>
     */
    int getToServerID();

    // required int32 code = 3;
    /**
     * <code>required int32 code = 3;</code>
     *
     * <pre>
     * 转发的协议号
     * </pre>
     */
    boolean hasCode();
    /**
     * <code>required int32 code = 3;</code>
     *
     * <pre>
     * 转发的协议号
     * </pre>
     */
    int getCode();

    // optional bytes body = 4;
    /**
     * <code>optional bytes body = 4;</code>
     *
     * <pre>
     * 转发的胞体
     * </pre>
     */
    boolean hasBody();
    /**
     * <code>optional bytes body = 4;</code>
     *
     * <pre>
     * 转发的胞体
     * </pre>
     */
    com.google.protobuf.ByteString getBody();
  }
  /**
   * Protobuf type {@code com.game.proto.CSForwardMsg}
   */
  public static final class CSForwardMsg extends
      com.google.protobuf.GeneratedMessage
      implements CSForwardMsgOrBuilder {
    // Use CSForwardMsg.newBuilder() to construct.
    private CSForwardMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private CSForwardMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final CSForwardMsg defaultInstance;
    public static CSForwardMsg getDefaultInstance() {
      return defaultInstance;
    }

    public CSForwardMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private CSForwardMsg(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              fromServerID_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              toServerID_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              code_ = input.readInt32();
              break;
            }
            case 34: {
              bitField0_ |= 0x00000008;
              body_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.game.pb.CenterMsgProto.internal_static_com_game_proto_CSForwardMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.game.pb.CenterMsgProto.internal_static_com_game_proto_CSForwardMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.game.pb.CenterMsgProto.CSForwardMsg.class, com.game.pb.CenterMsgProto.CSForwardMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<CSForwardMsg> PARSER =
        new com.google.protobuf.AbstractParser<CSForwardMsg>() {
      public CSForwardMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CSForwardMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<CSForwardMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 fromServerID = 1;
    public static final int FROMSERVERID_FIELD_NUMBER = 1;
    private int fromServerID_;
    /**
     * <code>required int32 fromServerID = 1;</code>
     *
     * <pre>
     * 转发消息始服务器
     * </pre>
     */
    public boolean hasFromServerID() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 fromServerID = 1;</code>
     *
     * <pre>
     * 转发消息始服务器
     * </pre>
     */
    public int getFromServerID() {
      return fromServerID_;
    }

    // required int32 toServerID = 2;
    public static final int TOSERVERID_FIELD_NUMBER = 2;
    private int toServerID_;
    /**
     * <code>required int32 toServerID = 2;</code>
     *
     * <pre>
     * 转发消息终服务器
     * </pre>
     */
    public boolean hasToServerID() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 toServerID = 2;</code>
     *
     * <pre>
     * 转发消息终服务器
     * </pre>
     */
    public int getToServerID() {
      return toServerID_;
    }

    // required int32 code = 3;
    public static final int CODE_FIELD_NUMBER = 3;
    private int code_;
    /**
     * <code>required int32 code = 3;</code>
     *
     * <pre>
     * 转发的协议号
     * </pre>
     */
    public boolean hasCode() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int32 code = 3;</code>
     *
     * <pre>
     * 转发的协议号
     * </pre>
     */
    public int getCode() {
      return code_;
    }

    // optional bytes body = 4;
    public static final int BODY_FIELD_NUMBER = 4;
    private com.google.protobuf.ByteString body_;
    /**
     * <code>optional bytes body = 4;</code>
     *
     * <pre>
     * 转发的胞体
     * </pre>
     */
    public boolean hasBody() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional bytes body = 4;</code>
     *
     * <pre>
     * 转发的胞体
     * </pre>
     */
    public com.google.protobuf.ByteString getBody() {
      return body_;
    }

    private void initFields() {
      fromServerID_ = 0;
      toServerID_ = 0;
      code_ = 0;
      body_ = com.google.protobuf.ByteString.EMPTY;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasFromServerID()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasToServerID()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasCode()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, fromServerID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, toServerID_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, code_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBytes(4, body_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, fromServerID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, toServerID_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, code_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(4, body_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.game.pb.CenterMsgProto.CSForwardMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.CenterMsgProto.CSForwardMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.CSForwardMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.CenterMsgProto.CSForwardMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.CSForwardMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.CenterMsgProto.CSForwardMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.CSForwardMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.game.pb.CenterMsgProto.CSForwardMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.CSForwardMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.CenterMsgProto.CSForwardMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.game.pb.CenterMsgProto.CSForwardMsg prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.game.proto.CSForwardMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.game.pb.CenterMsgProto.CSForwardMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.game.pb.CenterMsgProto.internal_static_com_game_proto_CSForwardMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.game.pb.CenterMsgProto.internal_static_com_game_proto_CSForwardMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.game.pb.CenterMsgProto.CSForwardMsg.class, com.game.pb.CenterMsgProto.CSForwardMsg.Builder.class);
      }

      // Construct using com.game.pb.CenterMsgProto.CSForwardMsg.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        fromServerID_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        toServerID_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        code_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        body_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.game.pb.CenterMsgProto.internal_static_com_game_proto_CSForwardMsg_descriptor;
      }

      public com.game.pb.CenterMsgProto.CSForwardMsg getDefaultInstanceForType() {
        return com.game.pb.CenterMsgProto.CSForwardMsg.getDefaultInstance();
      }

      public com.game.pb.CenterMsgProto.CSForwardMsg build() {
        com.game.pb.CenterMsgProto.CSForwardMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.game.pb.CenterMsgProto.CSForwardMsg buildPartial() {
        com.game.pb.CenterMsgProto.CSForwardMsg result = new com.game.pb.CenterMsgProto.CSForwardMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.fromServerID_ = fromServerID_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.toServerID_ = toServerID_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.code_ = code_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.body_ = body_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.game.pb.CenterMsgProto.CSForwardMsg) {
          return mergeFrom((com.game.pb.CenterMsgProto.CSForwardMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.game.pb.CenterMsgProto.CSForwardMsg other) {
        if (other == com.game.pb.CenterMsgProto.CSForwardMsg.getDefaultInstance()) return this;
        if (other.hasFromServerID()) {
          setFromServerID(other.getFromServerID());
        }
        if (other.hasToServerID()) {
          setToServerID(other.getToServerID());
        }
        if (other.hasCode()) {
          setCode(other.getCode());
        }
        if (other.hasBody()) {
          setBody(other.getBody());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasFromServerID()) {
          
          return false;
        }
        if (!hasToServerID()) {
          
          return false;
        }
        if (!hasCode()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.game.pb.CenterMsgProto.CSForwardMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.game.pb.CenterMsgProto.CSForwardMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 fromServerID = 1;
      private int fromServerID_ ;
      /**
       * <code>required int32 fromServerID = 1;</code>
       *
       * <pre>
       * 转发消息始服务器
       * </pre>
       */
      public boolean hasFromServerID() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 fromServerID = 1;</code>
       *
       * <pre>
       * 转发消息始服务器
       * </pre>
       */
      public int getFromServerID() {
        return fromServerID_;
      }
      /**
       * <code>required int32 fromServerID = 1;</code>
       *
       * <pre>
       * 转发消息始服务器
       * </pre>
       */
      public Builder setFromServerID(int value) {
        bitField0_ |= 0x00000001;
        fromServerID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 fromServerID = 1;</code>
       *
       * <pre>
       * 转发消息始服务器
       * </pre>
       */
      public Builder clearFromServerID() {
        bitField0_ = (bitField0_ & ~0x00000001);
        fromServerID_ = 0;
        onChanged();
        return this;
      }

      // required int32 toServerID = 2;
      private int toServerID_ ;
      /**
       * <code>required int32 toServerID = 2;</code>
       *
       * <pre>
       * 转发消息终服务器
       * </pre>
       */
      public boolean hasToServerID() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 toServerID = 2;</code>
       *
       * <pre>
       * 转发消息终服务器
       * </pre>
       */
      public int getToServerID() {
        return toServerID_;
      }
      /**
       * <code>required int32 toServerID = 2;</code>
       *
       * <pre>
       * 转发消息终服务器
       * </pre>
       */
      public Builder setToServerID(int value) {
        bitField0_ |= 0x00000002;
        toServerID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 toServerID = 2;</code>
       *
       * <pre>
       * 转发消息终服务器
       * </pre>
       */
      public Builder clearToServerID() {
        bitField0_ = (bitField0_ & ~0x00000002);
        toServerID_ = 0;
        onChanged();
        return this;
      }

      // required int32 code = 3;
      private int code_ ;
      /**
       * <code>required int32 code = 3;</code>
       *
       * <pre>
       * 转发的协议号
       * </pre>
       */
      public boolean hasCode() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required int32 code = 3;</code>
       *
       * <pre>
       * 转发的协议号
       * </pre>
       */
      public int getCode() {
        return code_;
      }
      /**
       * <code>required int32 code = 3;</code>
       *
       * <pre>
       * 转发的协议号
       * </pre>
       */
      public Builder setCode(int value) {
        bitField0_ |= 0x00000004;
        code_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 code = 3;</code>
       *
       * <pre>
       * 转发的协议号
       * </pre>
       */
      public Builder clearCode() {
        bitField0_ = (bitField0_ & ~0x00000004);
        code_ = 0;
        onChanged();
        return this;
      }

      // optional bytes body = 4;
      private com.google.protobuf.ByteString body_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>optional bytes body = 4;</code>
       *
       * <pre>
       * 转发的胞体
       * </pre>
       */
      public boolean hasBody() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional bytes body = 4;</code>
       *
       * <pre>
       * 转发的胞体
       * </pre>
       */
      public com.google.protobuf.ByteString getBody() {
        return body_;
      }
      /**
       * <code>optional bytes body = 4;</code>
       *
       * <pre>
       * 转发的胞体
       * </pre>
       */
      public Builder setBody(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        body_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bytes body = 4;</code>
       *
       * <pre>
       * 转发的胞体
       * </pre>
       */
      public Builder clearBody() {
        bitField0_ = (bitField0_ & ~0x00000008);
        body_ = getDefaultInstance().getBody();
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.game.proto.CSForwardMsg)
    }

    static {
      defaultInstance = new CSForwardMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.game.proto.CSForwardMsg)
  }

  public interface MsgToUSerOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // optional int32 userID = 1;
    /**
     * <code>optional int32 userID = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    boolean hasUserID();
    /**
     * <code>optional int32 userID = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    int getUserID();

    // optional bytes body = 2;
    /**
     * <code>optional bytes body = 2;</code>
     *
     * <pre>
     * 包体
     * </pre>
     */
    boolean hasBody();
    /**
     * <code>optional bytes body = 2;</code>
     *
     * <pre>
     * 包体
     * </pre>
     */
    com.google.protobuf.ByteString getBody();
  }
  /**
   * Protobuf type {@code com.game.proto.MsgToUSer}
   */
  public static final class MsgToUSer extends
      com.google.protobuf.GeneratedMessage
      implements MsgToUSerOrBuilder {
    // Use MsgToUSer.newBuilder() to construct.
    private MsgToUSer(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private MsgToUSer(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final MsgToUSer defaultInstance;
    public static MsgToUSer getDefaultInstance() {
      return defaultInstance;
    }

    public MsgToUSer getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private MsgToUSer(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              userID_ = input.readInt32();
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              body_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.game.pb.CenterMsgProto.internal_static_com_game_proto_MsgToUSer_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.game.pb.CenterMsgProto.internal_static_com_game_proto_MsgToUSer_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.game.pb.CenterMsgProto.MsgToUSer.class, com.game.pb.CenterMsgProto.MsgToUSer.Builder.class);
    }

    public static com.google.protobuf.Parser<MsgToUSer> PARSER =
        new com.google.protobuf.AbstractParser<MsgToUSer>() {
      public MsgToUSer parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new MsgToUSer(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<MsgToUSer> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // optional int32 userID = 1;
    public static final int USERID_FIELD_NUMBER = 1;
    private int userID_;
    /**
     * <code>optional int32 userID = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    public boolean hasUserID() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 userID = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    public int getUserID() {
      return userID_;
    }

    // optional bytes body = 2;
    public static final int BODY_FIELD_NUMBER = 2;
    private com.google.protobuf.ByteString body_;
    /**
     * <code>optional bytes body = 2;</code>
     *
     * <pre>
     * 包体
     * </pre>
     */
    public boolean hasBody() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional bytes body = 2;</code>
     *
     * <pre>
     * 包体
     * </pre>
     */
    public com.google.protobuf.ByteString getBody() {
      return body_;
    }

    private void initFields() {
      userID_ = 0;
      body_ = com.google.protobuf.ByteString.EMPTY;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, userID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, body_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, userID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, body_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.game.pb.CenterMsgProto.MsgToUSer parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.CenterMsgProto.MsgToUSer parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.MsgToUSer parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.CenterMsgProto.MsgToUSer parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.MsgToUSer parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.CenterMsgProto.MsgToUSer parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.MsgToUSer parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.game.pb.CenterMsgProto.MsgToUSer parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.MsgToUSer parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.CenterMsgProto.MsgToUSer parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.game.pb.CenterMsgProto.MsgToUSer prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.game.proto.MsgToUSer}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.game.pb.CenterMsgProto.MsgToUSerOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.game.pb.CenterMsgProto.internal_static_com_game_proto_MsgToUSer_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.game.pb.CenterMsgProto.internal_static_com_game_proto_MsgToUSer_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.game.pb.CenterMsgProto.MsgToUSer.class, com.game.pb.CenterMsgProto.MsgToUSer.Builder.class);
      }

      // Construct using com.game.pb.CenterMsgProto.MsgToUSer.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        userID_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        body_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.game.pb.CenterMsgProto.internal_static_com_game_proto_MsgToUSer_descriptor;
      }

      public com.game.pb.CenterMsgProto.MsgToUSer getDefaultInstanceForType() {
        return com.game.pb.CenterMsgProto.MsgToUSer.getDefaultInstance();
      }

      public com.game.pb.CenterMsgProto.MsgToUSer build() {
        com.game.pb.CenterMsgProto.MsgToUSer result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.game.pb.CenterMsgProto.MsgToUSer buildPartial() {
        com.game.pb.CenterMsgProto.MsgToUSer result = new com.game.pb.CenterMsgProto.MsgToUSer(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.userID_ = userID_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.body_ = body_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.game.pb.CenterMsgProto.MsgToUSer) {
          return mergeFrom((com.game.pb.CenterMsgProto.MsgToUSer)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.game.pb.CenterMsgProto.MsgToUSer other) {
        if (other == com.game.pb.CenterMsgProto.MsgToUSer.getDefaultInstance()) return this;
        if (other.hasUserID()) {
          setUserID(other.getUserID());
        }
        if (other.hasBody()) {
          setBody(other.getBody());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.game.pb.CenterMsgProto.MsgToUSer parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.game.pb.CenterMsgProto.MsgToUSer) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // optional int32 userID = 1;
      private int userID_ ;
      /**
       * <code>optional int32 userID = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public boolean hasUserID() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 userID = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public int getUserID() {
        return userID_;
      }
      /**
       * <code>optional int32 userID = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public Builder setUserID(int value) {
        bitField0_ |= 0x00000001;
        userID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 userID = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public Builder clearUserID() {
        bitField0_ = (bitField0_ & ~0x00000001);
        userID_ = 0;
        onChanged();
        return this;
      }

      // optional bytes body = 2;
      private com.google.protobuf.ByteString body_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>optional bytes body = 2;</code>
       *
       * <pre>
       * 包体
       * </pre>
       */
      public boolean hasBody() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional bytes body = 2;</code>
       *
       * <pre>
       * 包体
       * </pre>
       */
      public com.google.protobuf.ByteString getBody() {
        return body_;
      }
      /**
       * <code>optional bytes body = 2;</code>
       *
       * <pre>
       * 包体
       * </pre>
       */
      public Builder setBody(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        body_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bytes body = 2;</code>
       *
       * <pre>
       * 包体
       * </pre>
       */
      public Builder clearBody() {
        bitField0_ = (bitField0_ & ~0x00000002);
        body_ = getDefaultInstance().getBody();
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.game.proto.MsgToUSer)
    }

    static {
      defaultInstance = new MsgToUSer(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.game.proto.MsgToUSer)
  }

  public interface RegisterMsgOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 serverID = 1;
    /**
     * <code>required int32 serverID = 1;</code>
     */
    boolean hasServerID();
    /**
     * <code>required int32 serverID = 1;</code>
     */
    int getServerID();
  }
  /**
   * Protobuf type {@code com.game.proto.RegisterMsg}
   */
  public static final class RegisterMsg extends
      com.google.protobuf.GeneratedMessage
      implements RegisterMsgOrBuilder {
    // Use RegisterMsg.newBuilder() to construct.
    private RegisterMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private RegisterMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final RegisterMsg defaultInstance;
    public static RegisterMsg getDefaultInstance() {
      return defaultInstance;
    }

    public RegisterMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private RegisterMsg(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              serverID_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.game.pb.CenterMsgProto.internal_static_com_game_proto_RegisterMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.game.pb.CenterMsgProto.internal_static_com_game_proto_RegisterMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.game.pb.CenterMsgProto.RegisterMsg.class, com.game.pb.CenterMsgProto.RegisterMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<RegisterMsg> PARSER =
        new com.google.protobuf.AbstractParser<RegisterMsg>() {
      public RegisterMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new RegisterMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<RegisterMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 serverID = 1;
    public static final int SERVERID_FIELD_NUMBER = 1;
    private int serverID_;
    /**
     * <code>required int32 serverID = 1;</code>
     */
    public boolean hasServerID() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 serverID = 1;</code>
     */
    public int getServerID() {
      return serverID_;
    }

    private void initFields() {
      serverID_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasServerID()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, serverID_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, serverID_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.game.pb.CenterMsgProto.RegisterMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.CenterMsgProto.RegisterMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.RegisterMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.CenterMsgProto.RegisterMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.RegisterMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.CenterMsgProto.RegisterMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.RegisterMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.game.pb.CenterMsgProto.RegisterMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.RegisterMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.CenterMsgProto.RegisterMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.game.pb.CenterMsgProto.RegisterMsg prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.game.proto.RegisterMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.game.pb.CenterMsgProto.RegisterMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.game.pb.CenterMsgProto.internal_static_com_game_proto_RegisterMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.game.pb.CenterMsgProto.internal_static_com_game_proto_RegisterMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.game.pb.CenterMsgProto.RegisterMsg.class, com.game.pb.CenterMsgProto.RegisterMsg.Builder.class);
      }

      // Construct using com.game.pb.CenterMsgProto.RegisterMsg.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        serverID_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.game.pb.CenterMsgProto.internal_static_com_game_proto_RegisterMsg_descriptor;
      }

      public com.game.pb.CenterMsgProto.RegisterMsg getDefaultInstanceForType() {
        return com.game.pb.CenterMsgProto.RegisterMsg.getDefaultInstance();
      }

      public com.game.pb.CenterMsgProto.RegisterMsg build() {
        com.game.pb.CenterMsgProto.RegisterMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.game.pb.CenterMsgProto.RegisterMsg buildPartial() {
        com.game.pb.CenterMsgProto.RegisterMsg result = new com.game.pb.CenterMsgProto.RegisterMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.serverID_ = serverID_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.game.pb.CenterMsgProto.RegisterMsg) {
          return mergeFrom((com.game.pb.CenterMsgProto.RegisterMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.game.pb.CenterMsgProto.RegisterMsg other) {
        if (other == com.game.pb.CenterMsgProto.RegisterMsg.getDefaultInstance()) return this;
        if (other.hasServerID()) {
          setServerID(other.getServerID());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasServerID()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.game.pb.CenterMsgProto.RegisterMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.game.pb.CenterMsgProto.RegisterMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 serverID = 1;
      private int serverID_ ;
      /**
       * <code>required int32 serverID = 1;</code>
       */
      public boolean hasServerID() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 serverID = 1;</code>
       */
      public int getServerID() {
        return serverID_;
      }
      /**
       * <code>required int32 serverID = 1;</code>
       */
      public Builder setServerID(int value) {
        bitField0_ |= 0x00000001;
        serverID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 serverID = 1;</code>
       */
      public Builder clearServerID() {
        bitField0_ = (bitField0_ & ~0x00000001);
        serverID_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.game.proto.RegisterMsg)
    }

    static {
      defaultInstance = new RegisterMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.game.proto.RegisterMsg)
  }

  public interface PlayerDisconnectMsgOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 userID = 1;
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    boolean hasUserID();
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    int getUserID();

    // optional bool shutDown = 2;
    /**
     * <code>optional bool shutDown = 2;</code>
     *
     * <pre>
     * 是否立即关闭
     * </pre>
     */
    boolean hasShutDown();
    /**
     * <code>optional bool shutDown = 2;</code>
     *
     * <pre>
     * 是否立即关闭
     * </pre>
     */
    boolean getShutDown();

    // optional bool sendLoinOut = 3;
    /**
     * <code>optional bool sendLoinOut = 3;</code>
     *
     * <pre>
     * 是否登出
     * </pre>
     */
    boolean hasSendLoinOut();
    /**
     * <code>optional bool sendLoinOut = 3;</code>
     *
     * <pre>
     * 是否登出
     * </pre>
     */
    boolean getSendLoinOut();

    // optional int32 type = 4;
    /**
     * <code>optional int32 type = 4;</code>
     *
     * <pre>
     * 提示类型
     * </pre>
     */
    boolean hasType();
    /**
     * <code>optional int32 type = 4;</code>
     *
     * <pre>
     * 提示类型
     * </pre>
     */
    int getType();
  }
  /**
   * Protobuf type {@code com.game.proto.PlayerDisconnectMsg}
   */
  public static final class PlayerDisconnectMsg extends
      com.google.protobuf.GeneratedMessage
      implements PlayerDisconnectMsgOrBuilder {
    // Use PlayerDisconnectMsg.newBuilder() to construct.
    private PlayerDisconnectMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private PlayerDisconnectMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final PlayerDisconnectMsg defaultInstance;
    public static PlayerDisconnectMsg getDefaultInstance() {
      return defaultInstance;
    }

    public PlayerDisconnectMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private PlayerDisconnectMsg(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              userID_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              shutDown_ = input.readBool();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              sendLoinOut_ = input.readBool();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              type_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.game.pb.CenterMsgProto.internal_static_com_game_proto_PlayerDisconnectMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.game.pb.CenterMsgProto.internal_static_com_game_proto_PlayerDisconnectMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.game.pb.CenterMsgProto.PlayerDisconnectMsg.class, com.game.pb.CenterMsgProto.PlayerDisconnectMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<PlayerDisconnectMsg> PARSER =
        new com.google.protobuf.AbstractParser<PlayerDisconnectMsg>() {
      public PlayerDisconnectMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PlayerDisconnectMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<PlayerDisconnectMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 userID = 1;
    public static final int USERID_FIELD_NUMBER = 1;
    private int userID_;
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    public boolean hasUserID() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    public int getUserID() {
      return userID_;
    }

    // optional bool shutDown = 2;
    public static final int SHUTDOWN_FIELD_NUMBER = 2;
    private boolean shutDown_;
    /**
     * <code>optional bool shutDown = 2;</code>
     *
     * <pre>
     * 是否立即关闭
     * </pre>
     */
    public boolean hasShutDown() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional bool shutDown = 2;</code>
     *
     * <pre>
     * 是否立即关闭
     * </pre>
     */
    public boolean getShutDown() {
      return shutDown_;
    }

    // optional bool sendLoinOut = 3;
    public static final int SENDLOINOUT_FIELD_NUMBER = 3;
    private boolean sendLoinOut_;
    /**
     * <code>optional bool sendLoinOut = 3;</code>
     *
     * <pre>
     * 是否登出
     * </pre>
     */
    public boolean hasSendLoinOut() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional bool sendLoinOut = 3;</code>
     *
     * <pre>
     * 是否登出
     * </pre>
     */
    public boolean getSendLoinOut() {
      return sendLoinOut_;
    }

    // optional int32 type = 4;
    public static final int TYPE_FIELD_NUMBER = 4;
    private int type_;
    /**
     * <code>optional int32 type = 4;</code>
     *
     * <pre>
     * 提示类型
     * </pre>
     */
    public boolean hasType() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 type = 4;</code>
     *
     * <pre>
     * 提示类型
     * </pre>
     */
    public int getType() {
      return type_;
    }

    private void initFields() {
      userID_ = 0;
      shutDown_ = false;
      sendLoinOut_ = false;
      type_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasUserID()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, userID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBool(2, shutDown_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBool(3, sendLoinOut_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, type_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, userID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(2, shutDown_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(3, sendLoinOut_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, type_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.game.pb.CenterMsgProto.PlayerDisconnectMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.CenterMsgProto.PlayerDisconnectMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.PlayerDisconnectMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.CenterMsgProto.PlayerDisconnectMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.PlayerDisconnectMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.CenterMsgProto.PlayerDisconnectMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.PlayerDisconnectMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.game.pb.CenterMsgProto.PlayerDisconnectMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.PlayerDisconnectMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.CenterMsgProto.PlayerDisconnectMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.game.pb.CenterMsgProto.PlayerDisconnectMsg prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.game.proto.PlayerDisconnectMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.game.pb.CenterMsgProto.PlayerDisconnectMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.game.pb.CenterMsgProto.internal_static_com_game_proto_PlayerDisconnectMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.game.pb.CenterMsgProto.internal_static_com_game_proto_PlayerDisconnectMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.game.pb.CenterMsgProto.PlayerDisconnectMsg.class, com.game.pb.CenterMsgProto.PlayerDisconnectMsg.Builder.class);
      }

      // Construct using com.game.pb.CenterMsgProto.PlayerDisconnectMsg.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        userID_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        shutDown_ = false;
        bitField0_ = (bitField0_ & ~0x00000002);
        sendLoinOut_ = false;
        bitField0_ = (bitField0_ & ~0x00000004);
        type_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.game.pb.CenterMsgProto.internal_static_com_game_proto_PlayerDisconnectMsg_descriptor;
      }

      public com.game.pb.CenterMsgProto.PlayerDisconnectMsg getDefaultInstanceForType() {
        return com.game.pb.CenterMsgProto.PlayerDisconnectMsg.getDefaultInstance();
      }

      public com.game.pb.CenterMsgProto.PlayerDisconnectMsg build() {
        com.game.pb.CenterMsgProto.PlayerDisconnectMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.game.pb.CenterMsgProto.PlayerDisconnectMsg buildPartial() {
        com.game.pb.CenterMsgProto.PlayerDisconnectMsg result = new com.game.pb.CenterMsgProto.PlayerDisconnectMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.userID_ = userID_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.shutDown_ = shutDown_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.sendLoinOut_ = sendLoinOut_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.type_ = type_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.game.pb.CenterMsgProto.PlayerDisconnectMsg) {
          return mergeFrom((com.game.pb.CenterMsgProto.PlayerDisconnectMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.game.pb.CenterMsgProto.PlayerDisconnectMsg other) {
        if (other == com.game.pb.CenterMsgProto.PlayerDisconnectMsg.getDefaultInstance()) return this;
        if (other.hasUserID()) {
          setUserID(other.getUserID());
        }
        if (other.hasShutDown()) {
          setShutDown(other.getShutDown());
        }
        if (other.hasSendLoinOut()) {
          setSendLoinOut(other.getSendLoinOut());
        }
        if (other.hasType()) {
          setType(other.getType());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasUserID()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.game.pb.CenterMsgProto.PlayerDisconnectMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.game.pb.CenterMsgProto.PlayerDisconnectMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 userID = 1;
      private int userID_ ;
      /**
       * <code>required int32 userID = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public boolean hasUserID() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 userID = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public int getUserID() {
        return userID_;
      }
      /**
       * <code>required int32 userID = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public Builder setUserID(int value) {
        bitField0_ |= 0x00000001;
        userID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 userID = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public Builder clearUserID() {
        bitField0_ = (bitField0_ & ~0x00000001);
        userID_ = 0;
        onChanged();
        return this;
      }

      // optional bool shutDown = 2;
      private boolean shutDown_ ;
      /**
       * <code>optional bool shutDown = 2;</code>
       *
       * <pre>
       * 是否立即关闭
       * </pre>
       */
      public boolean hasShutDown() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional bool shutDown = 2;</code>
       *
       * <pre>
       * 是否立即关闭
       * </pre>
       */
      public boolean getShutDown() {
        return shutDown_;
      }
      /**
       * <code>optional bool shutDown = 2;</code>
       *
       * <pre>
       * 是否立即关闭
       * </pre>
       */
      public Builder setShutDown(boolean value) {
        bitField0_ |= 0x00000002;
        shutDown_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bool shutDown = 2;</code>
       *
       * <pre>
       * 是否立即关闭
       * </pre>
       */
      public Builder clearShutDown() {
        bitField0_ = (bitField0_ & ~0x00000002);
        shutDown_ = false;
        onChanged();
        return this;
      }

      // optional bool sendLoinOut = 3;
      private boolean sendLoinOut_ ;
      /**
       * <code>optional bool sendLoinOut = 3;</code>
       *
       * <pre>
       * 是否登出
       * </pre>
       */
      public boolean hasSendLoinOut() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional bool sendLoinOut = 3;</code>
       *
       * <pre>
       * 是否登出
       * </pre>
       */
      public boolean getSendLoinOut() {
        return sendLoinOut_;
      }
      /**
       * <code>optional bool sendLoinOut = 3;</code>
       *
       * <pre>
       * 是否登出
       * </pre>
       */
      public Builder setSendLoinOut(boolean value) {
        bitField0_ |= 0x00000004;
        sendLoinOut_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bool sendLoinOut = 3;</code>
       *
       * <pre>
       * 是否登出
       * </pre>
       */
      public Builder clearSendLoinOut() {
        bitField0_ = (bitField0_ & ~0x00000004);
        sendLoinOut_ = false;
        onChanged();
        return this;
      }

      // optional int32 type = 4;
      private int type_ ;
      /**
       * <code>optional int32 type = 4;</code>
       *
       * <pre>
       * 提示类型
       * </pre>
       */
      public boolean hasType() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional int32 type = 4;</code>
       *
       * <pre>
       * 提示类型
       * </pre>
       */
      public int getType() {
        return type_;
      }
      /**
       * <code>optional int32 type = 4;</code>
       *
       * <pre>
       * 提示类型
       * </pre>
       */
      public Builder setType(int value) {
        bitField0_ |= 0x00000008;
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 type = 4;</code>
       *
       * <pre>
       * 提示类型
       * </pre>
       */
      public Builder clearType() {
        bitField0_ = (bitField0_ & ~0x00000008);
        type_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.game.proto.PlayerDisconnectMsg)
    }

    static {
      defaultInstance = new PlayerDisconnectMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.game.proto.PlayerDisconnectMsg)
  }

  public interface PlayerOnDisconnectMsgOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 userID = 1;
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    boolean hasUserID();
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    int getUserID();
  }
  /**
   * Protobuf type {@code com.game.proto.PlayerOnDisconnectMsg}
   */
  public static final class PlayerOnDisconnectMsg extends
      com.google.protobuf.GeneratedMessage
      implements PlayerOnDisconnectMsgOrBuilder {
    // Use PlayerOnDisconnectMsg.newBuilder() to construct.
    private PlayerOnDisconnectMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private PlayerOnDisconnectMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final PlayerOnDisconnectMsg defaultInstance;
    public static PlayerOnDisconnectMsg getDefaultInstance() {
      return defaultInstance;
    }

    public PlayerOnDisconnectMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private PlayerOnDisconnectMsg(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              userID_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.game.pb.CenterMsgProto.internal_static_com_game_proto_PlayerOnDisconnectMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.game.pb.CenterMsgProto.internal_static_com_game_proto_PlayerOnDisconnectMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg.class, com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<PlayerOnDisconnectMsg> PARSER =
        new com.google.protobuf.AbstractParser<PlayerOnDisconnectMsg>() {
      public PlayerOnDisconnectMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PlayerOnDisconnectMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<PlayerOnDisconnectMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 userID = 1;
    public static final int USERID_FIELD_NUMBER = 1;
    private int userID_;
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    public boolean hasUserID() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     * 玩家id
     * </pre>
     */
    public int getUserID() {
      return userID_;
    }

    private void initFields() {
      userID_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasUserID()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, userID_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, userID_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.game.proto.PlayerOnDisconnectMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.game.pb.CenterMsgProto.PlayerOnDisconnectMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.game.pb.CenterMsgProto.internal_static_com_game_proto_PlayerOnDisconnectMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.game.pb.CenterMsgProto.internal_static_com_game_proto_PlayerOnDisconnectMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg.class, com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg.Builder.class);
      }

      // Construct using com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        userID_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.game.pb.CenterMsgProto.internal_static_com_game_proto_PlayerOnDisconnectMsg_descriptor;
      }

      public com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg getDefaultInstanceForType() {
        return com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg.getDefaultInstance();
      }

      public com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg build() {
        com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg buildPartial() {
        com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg result = new com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.userID_ = userID_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg) {
          return mergeFrom((com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg other) {
        if (other == com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg.getDefaultInstance()) return this;
        if (other.hasUserID()) {
          setUserID(other.getUserID());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasUserID()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.game.pb.CenterMsgProto.PlayerOnDisconnectMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 userID = 1;
      private int userID_ ;
      /**
       * <code>required int32 userID = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public boolean hasUserID() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 userID = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public int getUserID() {
        return userID_;
      }
      /**
       * <code>required int32 userID = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public Builder setUserID(int value) {
        bitField0_ |= 0x00000001;
        userID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 userID = 1;</code>
       *
       * <pre>
       * 玩家id
       * </pre>
       */
      public Builder clearUserID() {
        bitField0_ = (bitField0_ & ~0x00000001);
        userID_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.game.proto.PlayerOnDisconnectMsg)
    }

    static {
      defaultInstance = new PlayerOnDisconnectMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.game.proto.PlayerOnDisconnectMsg)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_game_proto_CSForwardMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_game_proto_CSForwardMsg_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_game_proto_MsgToUSer_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_game_proto_MsgToUSer_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_game_proto_RegisterMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_game_proto_RegisterMsg_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_game_proto_PlayerDisconnectMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_game_proto_PlayerDisconnectMsg_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_game_proto_PlayerOnDisconnectMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_game_proto_PlayerOnDisconnectMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030proto/cs/CenterMsg.proto\022\016com.game.pro" +
      "to\"T\n\014CSForwardMsg\022\024\n\014fromServerID\030\001 \002(\005" +
      "\022\022\n\ntoServerID\030\002 \002(\005\022\014\n\004code\030\003 \002(\005\022\014\n\004bo" +
      "dy\030\004 \001(\014\")\n\tMsgToUSer\022\016\n\006userID\030\001 \001(\005\022\014\n" +
      "\004body\030\002 \001(\014\"\037\n\013RegisterMsg\022\020\n\010serverID\030\001" +
      " \002(\005\"Z\n\023PlayerDisconnectMsg\022\016\n\006userID\030\001 " +
      "\002(\005\022\020\n\010shutDown\030\002 \001(\010\022\023\n\013sendLoinOut\030\003 \001" +
      "(\010\022\014\n\004type\030\004 \001(\005\"\'\n\025PlayerOnDisconnectMs" +
      "g\022\016\n\006userID\030\001 \002(\005B\035\n\013com.game.pbB\016Center" +
      "MsgProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_com_game_proto_CSForwardMsg_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_com_game_proto_CSForwardMsg_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_game_proto_CSForwardMsg_descriptor,
              new java.lang.String[] { "FromServerID", "ToServerID", "Code", "Body", });
          internal_static_com_game_proto_MsgToUSer_descriptor =
            getDescriptor().getMessageTypes().get(1);
          internal_static_com_game_proto_MsgToUSer_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_game_proto_MsgToUSer_descriptor,
              new java.lang.String[] { "UserID", "Body", });
          internal_static_com_game_proto_RegisterMsg_descriptor =
            getDescriptor().getMessageTypes().get(2);
          internal_static_com_game_proto_RegisterMsg_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_game_proto_RegisterMsg_descriptor,
              new java.lang.String[] { "ServerID", });
          internal_static_com_game_proto_PlayerDisconnectMsg_descriptor =
            getDescriptor().getMessageTypes().get(3);
          internal_static_com_game_proto_PlayerDisconnectMsg_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_game_proto_PlayerDisconnectMsg_descriptor,
              new java.lang.String[] { "UserID", "ShutDown", "SendLoinOut", "Type", });
          internal_static_com_game_proto_PlayerOnDisconnectMsg_descriptor =
            getDescriptor().getMessageTypes().get(4);
          internal_static_com_game_proto_PlayerOnDisconnectMsg_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_game_proto_PlayerOnDisconnectMsg_descriptor,
              new java.lang.String[] { "UserID", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
