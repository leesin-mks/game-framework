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

public final class PlayerMsgProto {
  private PlayerMsgProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface LoginMsgCSOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 userID = 1;
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     *account name
     * </pre>
     */
    boolean hasUserID();
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     *account name
     * </pre>
     */
    int getUserID();

    // required string password = 2;
    /**
     * <code>required string password = 2;</code>
     *
     * <pre>
     * nickname
     * </pre>
     */
    boolean hasPassword();
    /**
     * <code>required string password = 2;</code>
     *
     * <pre>
     * nickname
     * </pre>
     */
    java.lang.String getPassword();
    /**
     * <code>required string password = 2;</code>
     *
     * <pre>
     * nickname
     * </pre>
     */
    com.google.protobuf.ByteString
        getPasswordBytes();

    // optional int32 channel = 3;
    /**
     * <code>optional int32 channel = 3;</code>
     *
     * <pre>
     * 渠道号
     * </pre>
     */
    boolean hasChannel();
    /**
     * <code>optional int32 channel = 3;</code>
     *
     * <pre>
     * 渠道号
     * </pre>
     */
    int getChannel();
  }
  /**
   * Protobuf type {@code com.game.proto.LoginMsgCS}
   */
  public static final class LoginMsgCS extends
      com.google.protobuf.GeneratedMessage
      implements LoginMsgCSOrBuilder {
    // Use LoginMsgCS.newBuilder() to construct.
    private LoginMsgCS(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private LoginMsgCS(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final LoginMsgCS defaultInstance;
    public static LoginMsgCS getDefaultInstance() {
      return defaultInstance;
    }

    public LoginMsgCS getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private LoginMsgCS(
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
              password_ = input.readBytes();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              channel_ = input.readInt32();
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
      return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_LoginMsgCS_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_LoginMsgCS_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.game.pb.PlayerMsgProto.LoginMsgCS.class, com.game.pb.PlayerMsgProto.LoginMsgCS.Builder.class);
    }

    public static com.google.protobuf.Parser<LoginMsgCS> PARSER =
        new com.google.protobuf.AbstractParser<LoginMsgCS>() {
      public LoginMsgCS parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new LoginMsgCS(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<LoginMsgCS> getParserForType() {
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
     *account name
     * </pre>
     */
    public boolean hasUserID() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     *account name
     * </pre>
     */
    public int getUserID() {
      return userID_;
    }

    // required string password = 2;
    public static final int PASSWORD_FIELD_NUMBER = 2;
    private java.lang.Object password_;
    /**
     * <code>required string password = 2;</code>
     *
     * <pre>
     * nickname
     * </pre>
     */
    public boolean hasPassword() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string password = 2;</code>
     *
     * <pre>
     * nickname
     * </pre>
     */
    public java.lang.String getPassword() {
      java.lang.Object ref = password_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          password_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string password = 2;</code>
     *
     * <pre>
     * nickname
     * </pre>
     */
    public com.google.protobuf.ByteString
        getPasswordBytes() {
      java.lang.Object ref = password_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        password_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // optional int32 channel = 3;
    public static final int CHANNEL_FIELD_NUMBER = 3;
    private int channel_;
    /**
     * <code>optional int32 channel = 3;</code>
     *
     * <pre>
     * 渠道号
     * </pre>
     */
    public boolean hasChannel() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 channel = 3;</code>
     *
     * <pre>
     * 渠道号
     * </pre>
     */
    public int getChannel() {
      return channel_;
    }

    private void initFields() {
      userID_ = 0;
      password_ = "";
      channel_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasUserID()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasPassword()) {
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
        output.writeBytes(2, getPasswordBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, channel_);
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
          .computeBytesSize(2, getPasswordBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, channel_);
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

    public static com.game.pb.PlayerMsgProto.LoginMsgCS parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgCS parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgCS parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgCS parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgCS parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgCS parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgCS parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgCS parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgCS parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgCS parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.game.pb.PlayerMsgProto.LoginMsgCS prototype) {
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
     * Protobuf type {@code com.game.proto.LoginMsgCS}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.game.pb.PlayerMsgProto.LoginMsgCSOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_LoginMsgCS_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_LoginMsgCS_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.game.pb.PlayerMsgProto.LoginMsgCS.class, com.game.pb.PlayerMsgProto.LoginMsgCS.Builder.class);
      }

      // Construct using com.game.pb.PlayerMsgProto.LoginMsgCS.newBuilder()
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
        password_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        channel_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_LoginMsgCS_descriptor;
      }

      public com.game.pb.PlayerMsgProto.LoginMsgCS getDefaultInstanceForType() {
        return com.game.pb.PlayerMsgProto.LoginMsgCS.getDefaultInstance();
      }

      public com.game.pb.PlayerMsgProto.LoginMsgCS build() {
        com.game.pb.PlayerMsgProto.LoginMsgCS result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.game.pb.PlayerMsgProto.LoginMsgCS buildPartial() {
        com.game.pb.PlayerMsgProto.LoginMsgCS result = new com.game.pb.PlayerMsgProto.LoginMsgCS(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.userID_ = userID_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.password_ = password_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.channel_ = channel_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.game.pb.PlayerMsgProto.LoginMsgCS) {
          return mergeFrom((com.game.pb.PlayerMsgProto.LoginMsgCS)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.game.pb.PlayerMsgProto.LoginMsgCS other) {
        if (other == com.game.pb.PlayerMsgProto.LoginMsgCS.getDefaultInstance()) return this;
        if (other.hasUserID()) {
          setUserID(other.getUserID());
        }
        if (other.hasPassword()) {
          bitField0_ |= 0x00000002;
          password_ = other.password_;
          onChanged();
        }
        if (other.hasChannel()) {
          setChannel(other.getChannel());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasUserID()) {
          
          return false;
        }
        if (!hasPassword()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.game.pb.PlayerMsgProto.LoginMsgCS parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.game.pb.PlayerMsgProto.LoginMsgCS) e.getUnfinishedMessage();
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
       *account name
       * </pre>
       */
      public boolean hasUserID() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 userID = 1;</code>
       *
       * <pre>
       *account name
       * </pre>
       */
      public int getUserID() {
        return userID_;
      }
      /**
       * <code>required int32 userID = 1;</code>
       *
       * <pre>
       *account name
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
       *account name
       * </pre>
       */
      public Builder clearUserID() {
        bitField0_ = (bitField0_ & ~0x00000001);
        userID_ = 0;
        onChanged();
        return this;
      }

      // required string password = 2;
      private java.lang.Object password_ = "";
      /**
       * <code>required string password = 2;</code>
       *
       * <pre>
       * nickname
       * </pre>
       */
      public boolean hasPassword() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string password = 2;</code>
       *
       * <pre>
       * nickname
       * </pre>
       */
      public java.lang.String getPassword() {
        java.lang.Object ref = password_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          password_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string password = 2;</code>
       *
       * <pre>
       * nickname
       * </pre>
       */
      public com.google.protobuf.ByteString
          getPasswordBytes() {
        java.lang.Object ref = password_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          password_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string password = 2;</code>
       *
       * <pre>
       * nickname
       * </pre>
       */
      public Builder setPassword(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        password_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string password = 2;</code>
       *
       * <pre>
       * nickname
       * </pre>
       */
      public Builder clearPassword() {
        bitField0_ = (bitField0_ & ~0x00000002);
        password_ = getDefaultInstance().getPassword();
        onChanged();
        return this;
      }
      /**
       * <code>required string password = 2;</code>
       *
       * <pre>
       * nickname
       * </pre>
       */
      public Builder setPasswordBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        password_ = value;
        onChanged();
        return this;
      }

      // optional int32 channel = 3;
      private int channel_ ;
      /**
       * <code>optional int32 channel = 3;</code>
       *
       * <pre>
       * 渠道号
       * </pre>
       */
      public boolean hasChannel() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 channel = 3;</code>
       *
       * <pre>
       * 渠道号
       * </pre>
       */
      public int getChannel() {
        return channel_;
      }
      /**
       * <code>optional int32 channel = 3;</code>
       *
       * <pre>
       * 渠道号
       * </pre>
       */
      public Builder setChannel(int value) {
        bitField0_ |= 0x00000004;
        channel_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 channel = 3;</code>
       *
       * <pre>
       * 渠道号
       * </pre>
       */
      public Builder clearChannel() {
        bitField0_ = (bitField0_ & ~0x00000004);
        channel_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.game.proto.LoginMsgCS)
    }

    static {
      defaultInstance = new LoginMsgCS(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.game.proto.LoginMsgCS)
  }

  public interface LoginMsgSCOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 status = 1;
    /**
     * <code>required int32 status = 1;</code>
     */
    boolean hasStatus();
    /**
     * <code>required int32 status = 1;</code>
     */
    int getStatus();

    // required string message = 2;
    /**
     * <code>required string message = 2;</code>
     */
    boolean hasMessage();
    /**
     * <code>required string message = 2;</code>
     */
    java.lang.String getMessage();
    /**
     * <code>required string message = 2;</code>
     */
    com.google.protobuf.ByteString
        getMessageBytes();

    // optional .com.game.proto.PlayerInfoPB playerInfos = 3;
    /**
     * <code>optional .com.game.proto.PlayerInfoPB playerInfos = 3;</code>
     */
    boolean hasPlayerInfos();
    /**
     * <code>optional .com.game.proto.PlayerInfoPB playerInfos = 3;</code>
     */
    com.game.pb.PlayerMsgProto.PlayerInfoPB getPlayerInfos();
    /**
     * <code>optional .com.game.proto.PlayerInfoPB playerInfos = 3;</code>
     */
    com.game.pb.PlayerMsgProto.PlayerInfoPBOrBuilder getPlayerInfosOrBuilder();
  }
  /**
   * Protobuf type {@code com.game.proto.LoginMsgSC}
   */
  public static final class LoginMsgSC extends
      com.google.protobuf.GeneratedMessage
      implements LoginMsgSCOrBuilder {
    // Use LoginMsgSC.newBuilder() to construct.
    private LoginMsgSC(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private LoginMsgSC(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final LoginMsgSC defaultInstance;
    public static LoginMsgSC getDefaultInstance() {
      return defaultInstance;
    }

    public LoginMsgSC getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private LoginMsgSC(
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
              status_ = input.readInt32();
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              message_ = input.readBytes();
              break;
            }
            case 26: {
              com.game.pb.PlayerMsgProto.PlayerInfoPB.Builder subBuilder = null;
              if (((bitField0_ & 0x00000004) == 0x00000004)) {
                subBuilder = playerInfos_.toBuilder();
              }
              playerInfos_ = input.readMessage(com.game.pb.PlayerMsgProto.PlayerInfoPB.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(playerInfos_);
                playerInfos_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000004;
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
      return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_LoginMsgSC_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_LoginMsgSC_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.game.pb.PlayerMsgProto.LoginMsgSC.class, com.game.pb.PlayerMsgProto.LoginMsgSC.Builder.class);
    }

    public static com.google.protobuf.Parser<LoginMsgSC> PARSER =
        new com.google.protobuf.AbstractParser<LoginMsgSC>() {
      public LoginMsgSC parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new LoginMsgSC(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<LoginMsgSC> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 status = 1;
    public static final int STATUS_FIELD_NUMBER = 1;
    private int status_;
    /**
     * <code>required int32 status = 1;</code>
     */
    public boolean hasStatus() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 status = 1;</code>
     */
    public int getStatus() {
      return status_;
    }

    // required string message = 2;
    public static final int MESSAGE_FIELD_NUMBER = 2;
    private java.lang.Object message_;
    /**
     * <code>required string message = 2;</code>
     */
    public boolean hasMessage() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string message = 2;</code>
     */
    public java.lang.String getMessage() {
      java.lang.Object ref = message_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          message_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string message = 2;</code>
     */
    public com.google.protobuf.ByteString
        getMessageBytes() {
      java.lang.Object ref = message_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // optional .com.game.proto.PlayerInfoPB playerInfos = 3;
    public static final int PLAYERINFOS_FIELD_NUMBER = 3;
    private com.game.pb.PlayerMsgProto.PlayerInfoPB playerInfos_;
    /**
     * <code>optional .com.game.proto.PlayerInfoPB playerInfos = 3;</code>
     */
    public boolean hasPlayerInfos() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional .com.game.proto.PlayerInfoPB playerInfos = 3;</code>
     */
    public com.game.pb.PlayerMsgProto.PlayerInfoPB getPlayerInfos() {
      return playerInfos_;
    }
    /**
     * <code>optional .com.game.proto.PlayerInfoPB playerInfos = 3;</code>
     */
    public com.game.pb.PlayerMsgProto.PlayerInfoPBOrBuilder getPlayerInfosOrBuilder() {
      return playerInfos_;
    }

    private void initFields() {
      status_ = 0;
      message_ = "";
      playerInfos_ = com.game.pb.PlayerMsgProto.PlayerInfoPB.getDefaultInstance();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasStatus()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasMessage()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (hasPlayerInfos()) {
        if (!getPlayerInfos().isInitialized()) {
          memoizedIsInitialized = 0;
          return false;
        }
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, status_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getMessageBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeMessage(3, playerInfos_);
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
          .computeInt32Size(1, status_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getMessageBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(3, playerInfos_);
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

    public static com.game.pb.PlayerMsgProto.LoginMsgSC parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgSC parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgSC parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgSC parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgSC parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgSC parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgSC parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgSC parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgSC parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.PlayerMsgProto.LoginMsgSC parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.game.pb.PlayerMsgProto.LoginMsgSC prototype) {
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
     * Protobuf type {@code com.game.proto.LoginMsgSC}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.game.pb.PlayerMsgProto.LoginMsgSCOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_LoginMsgSC_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_LoginMsgSC_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.game.pb.PlayerMsgProto.LoginMsgSC.class, com.game.pb.PlayerMsgProto.LoginMsgSC.Builder.class);
      }

      // Construct using com.game.pb.PlayerMsgProto.LoginMsgSC.newBuilder()
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
          getPlayerInfosFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        status_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        message_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        if (playerInfosBuilder_ == null) {
          playerInfos_ = com.game.pb.PlayerMsgProto.PlayerInfoPB.getDefaultInstance();
        } else {
          playerInfosBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_LoginMsgSC_descriptor;
      }

      public com.game.pb.PlayerMsgProto.LoginMsgSC getDefaultInstanceForType() {
        return com.game.pb.PlayerMsgProto.LoginMsgSC.getDefaultInstance();
      }

      public com.game.pb.PlayerMsgProto.LoginMsgSC build() {
        com.game.pb.PlayerMsgProto.LoginMsgSC result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.game.pb.PlayerMsgProto.LoginMsgSC buildPartial() {
        com.game.pb.PlayerMsgProto.LoginMsgSC result = new com.game.pb.PlayerMsgProto.LoginMsgSC(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.status_ = status_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.message_ = message_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        if (playerInfosBuilder_ == null) {
          result.playerInfos_ = playerInfos_;
        } else {
          result.playerInfos_ = playerInfosBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.game.pb.PlayerMsgProto.LoginMsgSC) {
          return mergeFrom((com.game.pb.PlayerMsgProto.LoginMsgSC)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.game.pb.PlayerMsgProto.LoginMsgSC other) {
        if (other == com.game.pb.PlayerMsgProto.LoginMsgSC.getDefaultInstance()) return this;
        if (other.hasStatus()) {
          setStatus(other.getStatus());
        }
        if (other.hasMessage()) {
          bitField0_ |= 0x00000002;
          message_ = other.message_;
          onChanged();
        }
        if (other.hasPlayerInfos()) {
          mergePlayerInfos(other.getPlayerInfos());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasStatus()) {
          
          return false;
        }
        if (!hasMessage()) {
          
          return false;
        }
        if (hasPlayerInfos()) {
          if (!getPlayerInfos().isInitialized()) {
            
            return false;
          }
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.game.pb.PlayerMsgProto.LoginMsgSC parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.game.pb.PlayerMsgProto.LoginMsgSC) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 status = 1;
      private int status_ ;
      /**
       * <code>required int32 status = 1;</code>
       */
      public boolean hasStatus() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 status = 1;</code>
       */
      public int getStatus() {
        return status_;
      }
      /**
       * <code>required int32 status = 1;</code>
       */
      public Builder setStatus(int value) {
        bitField0_ |= 0x00000001;
        status_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 status = 1;</code>
       */
      public Builder clearStatus() {
        bitField0_ = (bitField0_ & ~0x00000001);
        status_ = 0;
        onChanged();
        return this;
      }

      // required string message = 2;
      private java.lang.Object message_ = "";
      /**
       * <code>required string message = 2;</code>
       */
      public boolean hasMessage() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string message = 2;</code>
       */
      public java.lang.String getMessage() {
        java.lang.Object ref = message_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          message_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string message = 2;</code>
       */
      public com.google.protobuf.ByteString
          getMessageBytes() {
        java.lang.Object ref = message_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          message_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string message = 2;</code>
       */
      public Builder setMessage(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        message_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string message = 2;</code>
       */
      public Builder clearMessage() {
        bitField0_ = (bitField0_ & ~0x00000002);
        message_ = getDefaultInstance().getMessage();
        onChanged();
        return this;
      }
      /**
       * <code>required string message = 2;</code>
       */
      public Builder setMessageBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        message_ = value;
        onChanged();
        return this;
      }

      // optional .com.game.proto.PlayerInfoPB playerInfos = 3;
      private com.game.pb.PlayerMsgProto.PlayerInfoPB playerInfos_ = com.game.pb.PlayerMsgProto.PlayerInfoPB.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.game.pb.PlayerMsgProto.PlayerInfoPB, com.game.pb.PlayerMsgProto.PlayerInfoPB.Builder, com.game.pb.PlayerMsgProto.PlayerInfoPBOrBuilder> playerInfosBuilder_;
      /**
       * <code>optional .com.game.proto.PlayerInfoPB playerInfos = 3;</code>
       */
      public boolean hasPlayerInfos() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional .com.game.proto.PlayerInfoPB playerInfos = 3;</code>
       */
      public com.game.pb.PlayerMsgProto.PlayerInfoPB getPlayerInfos() {
        if (playerInfosBuilder_ == null) {
          return playerInfos_;
        } else {
          return playerInfosBuilder_.getMessage();
        }
      }
      /**
       * <code>optional .com.game.proto.PlayerInfoPB playerInfos = 3;</code>
       */
      public Builder setPlayerInfos(com.game.pb.PlayerMsgProto.PlayerInfoPB value) {
        if (playerInfosBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          playerInfos_ = value;
          onChanged();
        } else {
          playerInfosBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000004;
        return this;
      }
      /**
       * <code>optional .com.game.proto.PlayerInfoPB playerInfos = 3;</code>
       */
      public Builder setPlayerInfos(
          com.game.pb.PlayerMsgProto.PlayerInfoPB.Builder builderForValue) {
        if (playerInfosBuilder_ == null) {
          playerInfos_ = builderForValue.build();
          onChanged();
        } else {
          playerInfosBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000004;
        return this;
      }
      /**
       * <code>optional .com.game.proto.PlayerInfoPB playerInfos = 3;</code>
       */
      public Builder mergePlayerInfos(com.game.pb.PlayerMsgProto.PlayerInfoPB value) {
        if (playerInfosBuilder_ == null) {
          if (((bitField0_ & 0x00000004) == 0x00000004) &&
              playerInfos_ != com.game.pb.PlayerMsgProto.PlayerInfoPB.getDefaultInstance()) {
            playerInfos_ =
              com.game.pb.PlayerMsgProto.PlayerInfoPB.newBuilder(playerInfos_).mergeFrom(value).buildPartial();
          } else {
            playerInfos_ = value;
          }
          onChanged();
        } else {
          playerInfosBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000004;
        return this;
      }
      /**
       * <code>optional .com.game.proto.PlayerInfoPB playerInfos = 3;</code>
       */
      public Builder clearPlayerInfos() {
        if (playerInfosBuilder_ == null) {
          playerInfos_ = com.game.pb.PlayerMsgProto.PlayerInfoPB.getDefaultInstance();
          onChanged();
        } else {
          playerInfosBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }
      /**
       * <code>optional .com.game.proto.PlayerInfoPB playerInfos = 3;</code>
       */
      public com.game.pb.PlayerMsgProto.PlayerInfoPB.Builder getPlayerInfosBuilder() {
        bitField0_ |= 0x00000004;
        onChanged();
        return getPlayerInfosFieldBuilder().getBuilder();
      }
      /**
       * <code>optional .com.game.proto.PlayerInfoPB playerInfos = 3;</code>
       */
      public com.game.pb.PlayerMsgProto.PlayerInfoPBOrBuilder getPlayerInfosOrBuilder() {
        if (playerInfosBuilder_ != null) {
          return playerInfosBuilder_.getMessageOrBuilder();
        } else {
          return playerInfos_;
        }
      }
      /**
       * <code>optional .com.game.proto.PlayerInfoPB playerInfos = 3;</code>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.game.pb.PlayerMsgProto.PlayerInfoPB, com.game.pb.PlayerMsgProto.PlayerInfoPB.Builder, com.game.pb.PlayerMsgProto.PlayerInfoPBOrBuilder> 
          getPlayerInfosFieldBuilder() {
        if (playerInfosBuilder_ == null) {
          playerInfosBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.game.pb.PlayerMsgProto.PlayerInfoPB, com.game.pb.PlayerMsgProto.PlayerInfoPB.Builder, com.game.pb.PlayerMsgProto.PlayerInfoPBOrBuilder>(
                  playerInfos_,
                  getParentForChildren(),
                  isClean());
          playerInfos_ = null;
        }
        return playerInfosBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:com.game.proto.LoginMsgSC)
    }

    static {
      defaultInstance = new LoginMsgSC(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.game.proto.LoginMsgSC)
  }

  public interface PlayerInfoPBOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 userID = 1;
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     * 用户ID
     * </pre>
     */
    boolean hasUserID();
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     * 用户ID
     * </pre>
     */
    int getUserID();

    // optional string nickName = 2;
    /**
     * <code>optional string nickName = 2;</code>
     *
     * <pre>
     * 昵称
     * </pre>
     */
    boolean hasNickName();
    /**
     * <code>optional string nickName = 2;</code>
     *
     * <pre>
     * 昵称
     * </pre>
     */
    java.lang.String getNickName();
    /**
     * <code>optional string nickName = 2;</code>
     *
     * <pre>
     * 昵称
     * </pre>
     */
    com.google.protobuf.ByteString
        getNickNameBytes();

    // optional string head = 3;
    /**
     * <code>optional string head = 3;</code>
     *
     * <pre>
     * 用户头像
     * </pre>
     */
    boolean hasHead();
    /**
     * <code>optional string head = 3;</code>
     *
     * <pre>
     * 用户头像
     * </pre>
     */
    java.lang.String getHead();
    /**
     * <code>optional string head = 3;</code>
     *
     * <pre>
     * 用户头像
     * </pre>
     */
    com.google.protobuf.ByteString
        getHeadBytes();

    // optional string weChat = 4;
    /**
     * <code>optional string weChat = 4;</code>
     *
     * <pre>
     * 微信
     * </pre>
     */
    boolean hasWeChat();
    /**
     * <code>optional string weChat = 4;</code>
     *
     * <pre>
     * 微信
     * </pre>
     */
    java.lang.String getWeChat();
    /**
     * <code>optional string weChat = 4;</code>
     *
     * <pre>
     * 微信
     * </pre>
     */
    com.google.protobuf.ByteString
        getWeChatBytes();

    // optional string phone = 5;
    /**
     * <code>optional string phone = 5;</code>
     *
     * <pre>
     * 手机号
     * </pre>
     */
    boolean hasPhone();
    /**
     * <code>optional string phone = 5;</code>
     *
     * <pre>
     * 手机号
     * </pre>
     */
    java.lang.String getPhone();
    /**
     * <code>optional string phone = 5;</code>
     *
     * <pre>
     * 手机号
     * </pre>
     */
    com.google.protobuf.ByteString
        getPhoneBytes();

    // optional int64 nickChangeDate = 6;
    /**
     * <code>optional int64 nickChangeDate = 6;</code>
     *
     * <pre>
     * 上次修改昵称时间
     * </pre>
     */
    boolean hasNickChangeDate();
    /**
     * <code>optional int64 nickChangeDate = 6;</code>
     *
     * <pre>
     * 上次修改昵称时间
     * </pre>
     */
    long getNickChangeDate();

    // optional string unionID = 7;
    /**
     * <code>optional string unionID = 7;</code>
     *
     * <pre>
     * unionID
     * </pre>
     */
    boolean hasUnionID();
    /**
     * <code>optional string unionID = 7;</code>
     *
     * <pre>
     * unionID
     * </pre>
     */
    java.lang.String getUnionID();
    /**
     * <code>optional string unionID = 7;</code>
     *
     * <pre>
     * unionID
     * </pre>
     */
    com.google.protobuf.ByteString
        getUnionIDBytes();

    // optional int64 registerDate = 8;
    /**
     * <code>optional int64 registerDate = 8;</code>
     *
     * <pre>
     * 注册时间
     * </pre>
     */
    boolean hasRegisterDate();
    /**
     * <code>optional int64 registerDate = 8;</code>
     *
     * <pre>
     * 注册时间
     * </pre>
     */
    long getRegisterDate();
  }
  /**
   * Protobuf type {@code com.game.proto.PlayerInfoPB}
   */
  public static final class PlayerInfoPB extends
      com.google.protobuf.GeneratedMessage
      implements PlayerInfoPBOrBuilder {
    // Use PlayerInfoPB.newBuilder() to construct.
    private PlayerInfoPB(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private PlayerInfoPB(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final PlayerInfoPB defaultInstance;
    public static PlayerInfoPB getDefaultInstance() {
      return defaultInstance;
    }

    public PlayerInfoPB getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private PlayerInfoPB(
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
              nickName_ = input.readBytes();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              head_ = input.readBytes();
              break;
            }
            case 34: {
              bitField0_ |= 0x00000008;
              weChat_ = input.readBytes();
              break;
            }
            case 42: {
              bitField0_ |= 0x00000010;
              phone_ = input.readBytes();
              break;
            }
            case 48: {
              bitField0_ |= 0x00000020;
              nickChangeDate_ = input.readInt64();
              break;
            }
            case 58: {
              bitField0_ |= 0x00000040;
              unionID_ = input.readBytes();
              break;
            }
            case 64: {
              bitField0_ |= 0x00000080;
              registerDate_ = input.readInt64();
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
      return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_PlayerInfoPB_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_PlayerInfoPB_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.game.pb.PlayerMsgProto.PlayerInfoPB.class, com.game.pb.PlayerMsgProto.PlayerInfoPB.Builder.class);
    }

    public static com.google.protobuf.Parser<PlayerInfoPB> PARSER =
        new com.google.protobuf.AbstractParser<PlayerInfoPB>() {
      public PlayerInfoPB parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PlayerInfoPB(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<PlayerInfoPB> getParserForType() {
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
     * 用户ID
     * </pre>
     */
    public boolean hasUserID() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     * 用户ID
     * </pre>
     */
    public int getUserID() {
      return userID_;
    }

    // optional string nickName = 2;
    public static final int NICKNAME_FIELD_NUMBER = 2;
    private java.lang.Object nickName_;
    /**
     * <code>optional string nickName = 2;</code>
     *
     * <pre>
     * 昵称
     * </pre>
     */
    public boolean hasNickName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional string nickName = 2;</code>
     *
     * <pre>
     * 昵称
     * </pre>
     */
    public java.lang.String getNickName() {
      java.lang.Object ref = nickName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          nickName_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string nickName = 2;</code>
     *
     * <pre>
     * 昵称
     * </pre>
     */
    public com.google.protobuf.ByteString
        getNickNameBytes() {
      java.lang.Object ref = nickName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        nickName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // optional string head = 3;
    public static final int HEAD_FIELD_NUMBER = 3;
    private java.lang.Object head_;
    /**
     * <code>optional string head = 3;</code>
     *
     * <pre>
     * 用户头像
     * </pre>
     */
    public boolean hasHead() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional string head = 3;</code>
     *
     * <pre>
     * 用户头像
     * </pre>
     */
    public java.lang.String getHead() {
      java.lang.Object ref = head_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          head_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string head = 3;</code>
     *
     * <pre>
     * 用户头像
     * </pre>
     */
    public com.google.protobuf.ByteString
        getHeadBytes() {
      java.lang.Object ref = head_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        head_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // optional string weChat = 4;
    public static final int WECHAT_FIELD_NUMBER = 4;
    private java.lang.Object weChat_;
    /**
     * <code>optional string weChat = 4;</code>
     *
     * <pre>
     * 微信
     * </pre>
     */
    public boolean hasWeChat() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional string weChat = 4;</code>
     *
     * <pre>
     * 微信
     * </pre>
     */
    public java.lang.String getWeChat() {
      java.lang.Object ref = weChat_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          weChat_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string weChat = 4;</code>
     *
     * <pre>
     * 微信
     * </pre>
     */
    public com.google.protobuf.ByteString
        getWeChatBytes() {
      java.lang.Object ref = weChat_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        weChat_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // optional string phone = 5;
    public static final int PHONE_FIELD_NUMBER = 5;
    private java.lang.Object phone_;
    /**
     * <code>optional string phone = 5;</code>
     *
     * <pre>
     * 手机号
     * </pre>
     */
    public boolean hasPhone() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional string phone = 5;</code>
     *
     * <pre>
     * 手机号
     * </pre>
     */
    public java.lang.String getPhone() {
      java.lang.Object ref = phone_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          phone_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string phone = 5;</code>
     *
     * <pre>
     * 手机号
     * </pre>
     */
    public com.google.protobuf.ByteString
        getPhoneBytes() {
      java.lang.Object ref = phone_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        phone_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // optional int64 nickChangeDate = 6;
    public static final int NICKCHANGEDATE_FIELD_NUMBER = 6;
    private long nickChangeDate_;
    /**
     * <code>optional int64 nickChangeDate = 6;</code>
     *
     * <pre>
     * 上次修改昵称时间
     * </pre>
     */
    public boolean hasNickChangeDate() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <code>optional int64 nickChangeDate = 6;</code>
     *
     * <pre>
     * 上次修改昵称时间
     * </pre>
     */
    public long getNickChangeDate() {
      return nickChangeDate_;
    }

    // optional string unionID = 7;
    public static final int UNIONID_FIELD_NUMBER = 7;
    private java.lang.Object unionID_;
    /**
     * <code>optional string unionID = 7;</code>
     *
     * <pre>
     * unionID
     * </pre>
     */
    public boolean hasUnionID() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <code>optional string unionID = 7;</code>
     *
     * <pre>
     * unionID
     * </pre>
     */
    public java.lang.String getUnionID() {
      java.lang.Object ref = unionID_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          unionID_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string unionID = 7;</code>
     *
     * <pre>
     * unionID
     * </pre>
     */
    public com.google.protobuf.ByteString
        getUnionIDBytes() {
      java.lang.Object ref = unionID_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        unionID_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // optional int64 registerDate = 8;
    public static final int REGISTERDATE_FIELD_NUMBER = 8;
    private long registerDate_;
    /**
     * <code>optional int64 registerDate = 8;</code>
     *
     * <pre>
     * 注册时间
     * </pre>
     */
    public boolean hasRegisterDate() {
      return ((bitField0_ & 0x00000080) == 0x00000080);
    }
    /**
     * <code>optional int64 registerDate = 8;</code>
     *
     * <pre>
     * 注册时间
     * </pre>
     */
    public long getRegisterDate() {
      return registerDate_;
    }

    private void initFields() {
      userID_ = 0;
      nickName_ = "";
      head_ = "";
      weChat_ = "";
      phone_ = "";
      nickChangeDate_ = 0L;
      unionID_ = "";
      registerDate_ = 0L;
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
        output.writeBytes(2, getNickNameBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getHeadBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBytes(4, getWeChatBytes());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeBytes(5, getPhoneBytes());
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        output.writeInt64(6, nickChangeDate_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        output.writeBytes(7, getUnionIDBytes());
      }
      if (((bitField0_ & 0x00000080) == 0x00000080)) {
        output.writeInt64(8, registerDate_);
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
          .computeBytesSize(2, getNickNameBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getHeadBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(4, getWeChatBytes());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(5, getPhoneBytes());
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(6, nickChangeDate_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(7, getUnionIDBytes());
      }
      if (((bitField0_ & 0x00000080) == 0x00000080)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(8, registerDate_);
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

    public static com.game.pb.PlayerMsgProto.PlayerInfoPB parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.PlayerMsgProto.PlayerInfoPB parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.PlayerInfoPB parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.PlayerMsgProto.PlayerInfoPB parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.PlayerInfoPB parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.PlayerMsgProto.PlayerInfoPB parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.PlayerInfoPB parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.game.pb.PlayerMsgProto.PlayerInfoPB parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.PlayerInfoPB parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.PlayerMsgProto.PlayerInfoPB parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.game.pb.PlayerMsgProto.PlayerInfoPB prototype) {
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
     * Protobuf type {@code com.game.proto.PlayerInfoPB}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.game.pb.PlayerMsgProto.PlayerInfoPBOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_PlayerInfoPB_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_PlayerInfoPB_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.game.pb.PlayerMsgProto.PlayerInfoPB.class, com.game.pb.PlayerMsgProto.PlayerInfoPB.Builder.class);
      }

      // Construct using com.game.pb.PlayerMsgProto.PlayerInfoPB.newBuilder()
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
        nickName_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        head_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        weChat_ = "";
        bitField0_ = (bitField0_ & ~0x00000008);
        phone_ = "";
        bitField0_ = (bitField0_ & ~0x00000010);
        nickChangeDate_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000020);
        unionID_ = "";
        bitField0_ = (bitField0_ & ~0x00000040);
        registerDate_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000080);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_PlayerInfoPB_descriptor;
      }

      public com.game.pb.PlayerMsgProto.PlayerInfoPB getDefaultInstanceForType() {
        return com.game.pb.PlayerMsgProto.PlayerInfoPB.getDefaultInstance();
      }

      public com.game.pb.PlayerMsgProto.PlayerInfoPB build() {
        com.game.pb.PlayerMsgProto.PlayerInfoPB result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.game.pb.PlayerMsgProto.PlayerInfoPB buildPartial() {
        com.game.pb.PlayerMsgProto.PlayerInfoPB result = new com.game.pb.PlayerMsgProto.PlayerInfoPB(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.userID_ = userID_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.nickName_ = nickName_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.head_ = head_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.weChat_ = weChat_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.phone_ = phone_;
        if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
          to_bitField0_ |= 0x00000020;
        }
        result.nickChangeDate_ = nickChangeDate_;
        if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
          to_bitField0_ |= 0x00000040;
        }
        result.unionID_ = unionID_;
        if (((from_bitField0_ & 0x00000080) == 0x00000080)) {
          to_bitField0_ |= 0x00000080;
        }
        result.registerDate_ = registerDate_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.game.pb.PlayerMsgProto.PlayerInfoPB) {
          return mergeFrom((com.game.pb.PlayerMsgProto.PlayerInfoPB)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.game.pb.PlayerMsgProto.PlayerInfoPB other) {
        if (other == com.game.pb.PlayerMsgProto.PlayerInfoPB.getDefaultInstance()) return this;
        if (other.hasUserID()) {
          setUserID(other.getUserID());
        }
        if (other.hasNickName()) {
          bitField0_ |= 0x00000002;
          nickName_ = other.nickName_;
          onChanged();
        }
        if (other.hasHead()) {
          bitField0_ |= 0x00000004;
          head_ = other.head_;
          onChanged();
        }
        if (other.hasWeChat()) {
          bitField0_ |= 0x00000008;
          weChat_ = other.weChat_;
          onChanged();
        }
        if (other.hasPhone()) {
          bitField0_ |= 0x00000010;
          phone_ = other.phone_;
          onChanged();
        }
        if (other.hasNickChangeDate()) {
          setNickChangeDate(other.getNickChangeDate());
        }
        if (other.hasUnionID()) {
          bitField0_ |= 0x00000040;
          unionID_ = other.unionID_;
          onChanged();
        }
        if (other.hasRegisterDate()) {
          setRegisterDate(other.getRegisterDate());
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
        com.game.pb.PlayerMsgProto.PlayerInfoPB parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.game.pb.PlayerMsgProto.PlayerInfoPB) e.getUnfinishedMessage();
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
       * 用户ID
       * </pre>
       */
      public boolean hasUserID() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 userID = 1;</code>
       *
       * <pre>
       * 用户ID
       * </pre>
       */
      public int getUserID() {
        return userID_;
      }
      /**
       * <code>required int32 userID = 1;</code>
       *
       * <pre>
       * 用户ID
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
       * 用户ID
       * </pre>
       */
      public Builder clearUserID() {
        bitField0_ = (bitField0_ & ~0x00000001);
        userID_ = 0;
        onChanged();
        return this;
      }

      // optional string nickName = 2;
      private java.lang.Object nickName_ = "";
      /**
       * <code>optional string nickName = 2;</code>
       *
       * <pre>
       * 昵称
       * </pre>
       */
      public boolean hasNickName() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional string nickName = 2;</code>
       *
       * <pre>
       * 昵称
       * </pre>
       */
      public java.lang.String getNickName() {
        java.lang.Object ref = nickName_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          nickName_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string nickName = 2;</code>
       *
       * <pre>
       * 昵称
       * </pre>
       */
      public com.google.protobuf.ByteString
          getNickNameBytes() {
        java.lang.Object ref = nickName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          nickName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string nickName = 2;</code>
       *
       * <pre>
       * 昵称
       * </pre>
       */
      public Builder setNickName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        nickName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string nickName = 2;</code>
       *
       * <pre>
       * 昵称
       * </pre>
       */
      public Builder clearNickName() {
        bitField0_ = (bitField0_ & ~0x00000002);
        nickName_ = getDefaultInstance().getNickName();
        onChanged();
        return this;
      }
      /**
       * <code>optional string nickName = 2;</code>
       *
       * <pre>
       * 昵称
       * </pre>
       */
      public Builder setNickNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        nickName_ = value;
        onChanged();
        return this;
      }

      // optional string head = 3;
      private java.lang.Object head_ = "";
      /**
       * <code>optional string head = 3;</code>
       *
       * <pre>
       * 用户头像
       * </pre>
       */
      public boolean hasHead() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional string head = 3;</code>
       *
       * <pre>
       * 用户头像
       * </pre>
       */
      public java.lang.String getHead() {
        java.lang.Object ref = head_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          head_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string head = 3;</code>
       *
       * <pre>
       * 用户头像
       * </pre>
       */
      public com.google.protobuf.ByteString
          getHeadBytes() {
        java.lang.Object ref = head_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          head_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string head = 3;</code>
       *
       * <pre>
       * 用户头像
       * </pre>
       */
      public Builder setHead(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        head_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string head = 3;</code>
       *
       * <pre>
       * 用户头像
       * </pre>
       */
      public Builder clearHead() {
        bitField0_ = (bitField0_ & ~0x00000004);
        head_ = getDefaultInstance().getHead();
        onChanged();
        return this;
      }
      /**
       * <code>optional string head = 3;</code>
       *
       * <pre>
       * 用户头像
       * </pre>
       */
      public Builder setHeadBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        head_ = value;
        onChanged();
        return this;
      }

      // optional string weChat = 4;
      private java.lang.Object weChat_ = "";
      /**
       * <code>optional string weChat = 4;</code>
       *
       * <pre>
       * 微信
       * </pre>
       */
      public boolean hasWeChat() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional string weChat = 4;</code>
       *
       * <pre>
       * 微信
       * </pre>
       */
      public java.lang.String getWeChat() {
        java.lang.Object ref = weChat_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          weChat_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string weChat = 4;</code>
       *
       * <pre>
       * 微信
       * </pre>
       */
      public com.google.protobuf.ByteString
          getWeChatBytes() {
        java.lang.Object ref = weChat_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          weChat_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string weChat = 4;</code>
       *
       * <pre>
       * 微信
       * </pre>
       */
      public Builder setWeChat(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        weChat_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string weChat = 4;</code>
       *
       * <pre>
       * 微信
       * </pre>
       */
      public Builder clearWeChat() {
        bitField0_ = (bitField0_ & ~0x00000008);
        weChat_ = getDefaultInstance().getWeChat();
        onChanged();
        return this;
      }
      /**
       * <code>optional string weChat = 4;</code>
       *
       * <pre>
       * 微信
       * </pre>
       */
      public Builder setWeChatBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        weChat_ = value;
        onChanged();
        return this;
      }

      // optional string phone = 5;
      private java.lang.Object phone_ = "";
      /**
       * <code>optional string phone = 5;</code>
       *
       * <pre>
       * 手机号
       * </pre>
       */
      public boolean hasPhone() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>optional string phone = 5;</code>
       *
       * <pre>
       * 手机号
       * </pre>
       */
      public java.lang.String getPhone() {
        java.lang.Object ref = phone_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          phone_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string phone = 5;</code>
       *
       * <pre>
       * 手机号
       * </pre>
       */
      public com.google.protobuf.ByteString
          getPhoneBytes() {
        java.lang.Object ref = phone_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          phone_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string phone = 5;</code>
       *
       * <pre>
       * 手机号
       * </pre>
       */
      public Builder setPhone(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        phone_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string phone = 5;</code>
       *
       * <pre>
       * 手机号
       * </pre>
       */
      public Builder clearPhone() {
        bitField0_ = (bitField0_ & ~0x00000010);
        phone_ = getDefaultInstance().getPhone();
        onChanged();
        return this;
      }
      /**
       * <code>optional string phone = 5;</code>
       *
       * <pre>
       * 手机号
       * </pre>
       */
      public Builder setPhoneBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        phone_ = value;
        onChanged();
        return this;
      }

      // optional int64 nickChangeDate = 6;
      private long nickChangeDate_ ;
      /**
       * <code>optional int64 nickChangeDate = 6;</code>
       *
       * <pre>
       * 上次修改昵称时间
       * </pre>
       */
      public boolean hasNickChangeDate() {
        return ((bitField0_ & 0x00000020) == 0x00000020);
      }
      /**
       * <code>optional int64 nickChangeDate = 6;</code>
       *
       * <pre>
       * 上次修改昵称时间
       * </pre>
       */
      public long getNickChangeDate() {
        return nickChangeDate_;
      }
      /**
       * <code>optional int64 nickChangeDate = 6;</code>
       *
       * <pre>
       * 上次修改昵称时间
       * </pre>
       */
      public Builder setNickChangeDate(long value) {
        bitField0_ |= 0x00000020;
        nickChangeDate_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 nickChangeDate = 6;</code>
       *
       * <pre>
       * 上次修改昵称时间
       * </pre>
       */
      public Builder clearNickChangeDate() {
        bitField0_ = (bitField0_ & ~0x00000020);
        nickChangeDate_ = 0L;
        onChanged();
        return this;
      }

      // optional string unionID = 7;
      private java.lang.Object unionID_ = "";
      /**
       * <code>optional string unionID = 7;</code>
       *
       * <pre>
       * unionID
       * </pre>
       */
      public boolean hasUnionID() {
        return ((bitField0_ & 0x00000040) == 0x00000040);
      }
      /**
       * <code>optional string unionID = 7;</code>
       *
       * <pre>
       * unionID
       * </pre>
       */
      public java.lang.String getUnionID() {
        java.lang.Object ref = unionID_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          unionID_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string unionID = 7;</code>
       *
       * <pre>
       * unionID
       * </pre>
       */
      public com.google.protobuf.ByteString
          getUnionIDBytes() {
        java.lang.Object ref = unionID_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          unionID_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string unionID = 7;</code>
       *
       * <pre>
       * unionID
       * </pre>
       */
      public Builder setUnionID(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000040;
        unionID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string unionID = 7;</code>
       *
       * <pre>
       * unionID
       * </pre>
       */
      public Builder clearUnionID() {
        bitField0_ = (bitField0_ & ~0x00000040);
        unionID_ = getDefaultInstance().getUnionID();
        onChanged();
        return this;
      }
      /**
       * <code>optional string unionID = 7;</code>
       *
       * <pre>
       * unionID
       * </pre>
       */
      public Builder setUnionIDBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000040;
        unionID_ = value;
        onChanged();
        return this;
      }

      // optional int64 registerDate = 8;
      private long registerDate_ ;
      /**
       * <code>optional int64 registerDate = 8;</code>
       *
       * <pre>
       * 注册时间
       * </pre>
       */
      public boolean hasRegisterDate() {
        return ((bitField0_ & 0x00000080) == 0x00000080);
      }
      /**
       * <code>optional int64 registerDate = 8;</code>
       *
       * <pre>
       * 注册时间
       * </pre>
       */
      public long getRegisterDate() {
        return registerDate_;
      }
      /**
       * <code>optional int64 registerDate = 8;</code>
       *
       * <pre>
       * 注册时间
       * </pre>
       */
      public Builder setRegisterDate(long value) {
        bitField0_ |= 0x00000080;
        registerDate_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 registerDate = 8;</code>
       *
       * <pre>
       * 注册时间
       * </pre>
       */
      public Builder clearRegisterDate() {
        bitField0_ = (bitField0_ & ~0x00000080);
        registerDate_ = 0L;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.game.proto.PlayerInfoPB)
    }

    static {
      defaultInstance = new PlayerInfoPB(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.game.proto.PlayerInfoPB)
  }

  public interface UpdatePlayerInfoPBOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 userID = 1;
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     * 用户ID
     * </pre>
     */
    boolean hasUserID();
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     * 用户ID
     * </pre>
     */
    int getUserID();

    // required string nickName = 2;
    /**
     * <code>required string nickName = 2;</code>
     *
     * <pre>
     * 昵称
     * </pre>
     */
    boolean hasNickName();
    /**
     * <code>required string nickName = 2;</code>
     *
     * <pre>
     * 昵称
     * </pre>
     */
    java.lang.String getNickName();
    /**
     * <code>required string nickName = 2;</code>
     *
     * <pre>
     * 昵称
     * </pre>
     */
    com.google.protobuf.ByteString
        getNickNameBytes();

    // required string head = 3;
    /**
     * <code>required string head = 3;</code>
     *
     * <pre>
     * 用户头像
     * </pre>
     */
    boolean hasHead();
    /**
     * <code>required string head = 3;</code>
     *
     * <pre>
     * 用户头像
     * </pre>
     */
    java.lang.String getHead();
    /**
     * <code>required string head = 3;</code>
     *
     * <pre>
     * 用户头像
     * </pre>
     */
    com.google.protobuf.ByteString
        getHeadBytes();

    // required string weChat = 4;
    /**
     * <code>required string weChat = 4;</code>
     *
     * <pre>
     * 微信
     * </pre>
     */
    boolean hasWeChat();
    /**
     * <code>required string weChat = 4;</code>
     *
     * <pre>
     * 微信
     * </pre>
     */
    java.lang.String getWeChat();
    /**
     * <code>required string weChat = 4;</code>
     *
     * <pre>
     * 微信
     * </pre>
     */
    com.google.protobuf.ByteString
        getWeChatBytes();

    // required string phone = 5;
    /**
     * <code>required string phone = 5;</code>
     *
     * <pre>
     * 手机号
     * </pre>
     */
    boolean hasPhone();
    /**
     * <code>required string phone = 5;</code>
     *
     * <pre>
     * 手机号
     * </pre>
     */
    java.lang.String getPhone();
    /**
     * <code>required string phone = 5;</code>
     *
     * <pre>
     * 手机号
     * </pre>
     */
    com.google.protobuf.ByteString
        getPhoneBytes();

    // required int64 nickChangeDate = 6;
    /**
     * <code>required int64 nickChangeDate = 6;</code>
     *
     * <pre>
     * 上次修改昵称时间
     * </pre>
     */
    boolean hasNickChangeDate();
    /**
     * <code>required int64 nickChangeDate = 6;</code>
     *
     * <pre>
     * 上次修改昵称时间
     * </pre>
     */
    long getNickChangeDate();

    // required string unionID = 7;
    /**
     * <code>required string unionID = 7;</code>
     *
     * <pre>
     * unionID
     * </pre>
     */
    boolean hasUnionID();
    /**
     * <code>required string unionID = 7;</code>
     *
     * <pre>
     * unionID
     * </pre>
     */
    java.lang.String getUnionID();
    /**
     * <code>required string unionID = 7;</code>
     *
     * <pre>
     * unionID
     * </pre>
     */
    com.google.protobuf.ByteString
        getUnionIDBytes();

    // required int64 registerDate = 8;
    /**
     * <code>required int64 registerDate = 8;</code>
     *
     * <pre>
     * 注册时间
     * </pre>
     */
    boolean hasRegisterDate();
    /**
     * <code>required int64 registerDate = 8;</code>
     *
     * <pre>
     * 注册时间
     * </pre>
     */
    long getRegisterDate();
  }
  /**
   * Protobuf type {@code com.game.proto.UpdatePlayerInfoPB}
   */
  public static final class UpdatePlayerInfoPB extends
      com.google.protobuf.GeneratedMessage
      implements UpdatePlayerInfoPBOrBuilder {
    // Use UpdatePlayerInfoPB.newBuilder() to construct.
    private UpdatePlayerInfoPB(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private UpdatePlayerInfoPB(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final UpdatePlayerInfoPB defaultInstance;
    public static UpdatePlayerInfoPB getDefaultInstance() {
      return defaultInstance;
    }

    public UpdatePlayerInfoPB getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private UpdatePlayerInfoPB(
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
              nickName_ = input.readBytes();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              head_ = input.readBytes();
              break;
            }
            case 34: {
              bitField0_ |= 0x00000008;
              weChat_ = input.readBytes();
              break;
            }
            case 42: {
              bitField0_ |= 0x00000010;
              phone_ = input.readBytes();
              break;
            }
            case 48: {
              bitField0_ |= 0x00000020;
              nickChangeDate_ = input.readInt64();
              break;
            }
            case 58: {
              bitField0_ |= 0x00000040;
              unionID_ = input.readBytes();
              break;
            }
            case 64: {
              bitField0_ |= 0x00000080;
              registerDate_ = input.readInt64();
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
      return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_UpdatePlayerInfoPB_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_UpdatePlayerInfoPB_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB.class, com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB.Builder.class);
    }

    public static com.google.protobuf.Parser<UpdatePlayerInfoPB> PARSER =
        new com.google.protobuf.AbstractParser<UpdatePlayerInfoPB>() {
      public UpdatePlayerInfoPB parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new UpdatePlayerInfoPB(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<UpdatePlayerInfoPB> getParserForType() {
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
     * 用户ID
     * </pre>
     */
    public boolean hasUserID() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 userID = 1;</code>
     *
     * <pre>
     * 用户ID
     * </pre>
     */
    public int getUserID() {
      return userID_;
    }

    // required string nickName = 2;
    public static final int NICKNAME_FIELD_NUMBER = 2;
    private java.lang.Object nickName_;
    /**
     * <code>required string nickName = 2;</code>
     *
     * <pre>
     * 昵称
     * </pre>
     */
    public boolean hasNickName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string nickName = 2;</code>
     *
     * <pre>
     * 昵称
     * </pre>
     */
    public java.lang.String getNickName() {
      java.lang.Object ref = nickName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          nickName_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string nickName = 2;</code>
     *
     * <pre>
     * 昵称
     * </pre>
     */
    public com.google.protobuf.ByteString
        getNickNameBytes() {
      java.lang.Object ref = nickName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        nickName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required string head = 3;
    public static final int HEAD_FIELD_NUMBER = 3;
    private java.lang.Object head_;
    /**
     * <code>required string head = 3;</code>
     *
     * <pre>
     * 用户头像
     * </pre>
     */
    public boolean hasHead() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string head = 3;</code>
     *
     * <pre>
     * 用户头像
     * </pre>
     */
    public java.lang.String getHead() {
      java.lang.Object ref = head_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          head_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string head = 3;</code>
     *
     * <pre>
     * 用户头像
     * </pre>
     */
    public com.google.protobuf.ByteString
        getHeadBytes() {
      java.lang.Object ref = head_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        head_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required string weChat = 4;
    public static final int WECHAT_FIELD_NUMBER = 4;
    private java.lang.Object weChat_;
    /**
     * <code>required string weChat = 4;</code>
     *
     * <pre>
     * 微信
     * </pre>
     */
    public boolean hasWeChat() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required string weChat = 4;</code>
     *
     * <pre>
     * 微信
     * </pre>
     */
    public java.lang.String getWeChat() {
      java.lang.Object ref = weChat_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          weChat_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string weChat = 4;</code>
     *
     * <pre>
     * 微信
     * </pre>
     */
    public com.google.protobuf.ByteString
        getWeChatBytes() {
      java.lang.Object ref = weChat_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        weChat_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required string phone = 5;
    public static final int PHONE_FIELD_NUMBER = 5;
    private java.lang.Object phone_;
    /**
     * <code>required string phone = 5;</code>
     *
     * <pre>
     * 手机号
     * </pre>
     */
    public boolean hasPhone() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>required string phone = 5;</code>
     *
     * <pre>
     * 手机号
     * </pre>
     */
    public java.lang.String getPhone() {
      java.lang.Object ref = phone_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          phone_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string phone = 5;</code>
     *
     * <pre>
     * 手机号
     * </pre>
     */
    public com.google.protobuf.ByteString
        getPhoneBytes() {
      java.lang.Object ref = phone_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        phone_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required int64 nickChangeDate = 6;
    public static final int NICKCHANGEDATE_FIELD_NUMBER = 6;
    private long nickChangeDate_;
    /**
     * <code>required int64 nickChangeDate = 6;</code>
     *
     * <pre>
     * 上次修改昵称时间
     * </pre>
     */
    public boolean hasNickChangeDate() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <code>required int64 nickChangeDate = 6;</code>
     *
     * <pre>
     * 上次修改昵称时间
     * </pre>
     */
    public long getNickChangeDate() {
      return nickChangeDate_;
    }

    // required string unionID = 7;
    public static final int UNIONID_FIELD_NUMBER = 7;
    private java.lang.Object unionID_;
    /**
     * <code>required string unionID = 7;</code>
     *
     * <pre>
     * unionID
     * </pre>
     */
    public boolean hasUnionID() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <code>required string unionID = 7;</code>
     *
     * <pre>
     * unionID
     * </pre>
     */
    public java.lang.String getUnionID() {
      java.lang.Object ref = unionID_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          unionID_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string unionID = 7;</code>
     *
     * <pre>
     * unionID
     * </pre>
     */
    public com.google.protobuf.ByteString
        getUnionIDBytes() {
      java.lang.Object ref = unionID_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        unionID_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required int64 registerDate = 8;
    public static final int REGISTERDATE_FIELD_NUMBER = 8;
    private long registerDate_;
    /**
     * <code>required int64 registerDate = 8;</code>
     *
     * <pre>
     * 注册时间
     * </pre>
     */
    public boolean hasRegisterDate() {
      return ((bitField0_ & 0x00000080) == 0x00000080);
    }
    /**
     * <code>required int64 registerDate = 8;</code>
     *
     * <pre>
     * 注册时间
     * </pre>
     */
    public long getRegisterDate() {
      return registerDate_;
    }

    private void initFields() {
      userID_ = 0;
      nickName_ = "";
      head_ = "";
      weChat_ = "";
      phone_ = "";
      nickChangeDate_ = 0L;
      unionID_ = "";
      registerDate_ = 0L;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasUserID()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasNickName()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasHead()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasWeChat()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasPhone()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasNickChangeDate()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasUnionID()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasRegisterDate()) {
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
        output.writeBytes(2, getNickNameBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getHeadBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBytes(4, getWeChatBytes());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeBytes(5, getPhoneBytes());
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        output.writeInt64(6, nickChangeDate_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        output.writeBytes(7, getUnionIDBytes());
      }
      if (((bitField0_ & 0x00000080) == 0x00000080)) {
        output.writeInt64(8, registerDate_);
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
          .computeBytesSize(2, getNickNameBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getHeadBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(4, getWeChatBytes());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(5, getPhoneBytes());
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(6, nickChangeDate_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(7, getUnionIDBytes());
      }
      if (((bitField0_ & 0x00000080) == 0x00000080)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(8, registerDate_);
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

    public static com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB prototype) {
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
     * Protobuf type {@code com.game.proto.UpdatePlayerInfoPB}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.game.pb.PlayerMsgProto.UpdatePlayerInfoPBOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_UpdatePlayerInfoPB_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_UpdatePlayerInfoPB_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB.class, com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB.Builder.class);
      }

      // Construct using com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB.newBuilder()
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
        nickName_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        head_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        weChat_ = "";
        bitField0_ = (bitField0_ & ~0x00000008);
        phone_ = "";
        bitField0_ = (bitField0_ & ~0x00000010);
        nickChangeDate_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000020);
        unionID_ = "";
        bitField0_ = (bitField0_ & ~0x00000040);
        registerDate_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000080);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.game.pb.PlayerMsgProto.internal_static_com_game_proto_UpdatePlayerInfoPB_descriptor;
      }

      public com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB getDefaultInstanceForType() {
        return com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB.getDefaultInstance();
      }

      public com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB build() {
        com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB buildPartial() {
        com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB result = new com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.userID_ = userID_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.nickName_ = nickName_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.head_ = head_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.weChat_ = weChat_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.phone_ = phone_;
        if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
          to_bitField0_ |= 0x00000020;
        }
        result.nickChangeDate_ = nickChangeDate_;
        if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
          to_bitField0_ |= 0x00000040;
        }
        result.unionID_ = unionID_;
        if (((from_bitField0_ & 0x00000080) == 0x00000080)) {
          to_bitField0_ |= 0x00000080;
        }
        result.registerDate_ = registerDate_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB) {
          return mergeFrom((com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB other) {
        if (other == com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB.getDefaultInstance()) return this;
        if (other.hasUserID()) {
          setUserID(other.getUserID());
        }
        if (other.hasNickName()) {
          bitField0_ |= 0x00000002;
          nickName_ = other.nickName_;
          onChanged();
        }
        if (other.hasHead()) {
          bitField0_ |= 0x00000004;
          head_ = other.head_;
          onChanged();
        }
        if (other.hasWeChat()) {
          bitField0_ |= 0x00000008;
          weChat_ = other.weChat_;
          onChanged();
        }
        if (other.hasPhone()) {
          bitField0_ |= 0x00000010;
          phone_ = other.phone_;
          onChanged();
        }
        if (other.hasNickChangeDate()) {
          setNickChangeDate(other.getNickChangeDate());
        }
        if (other.hasUnionID()) {
          bitField0_ |= 0x00000040;
          unionID_ = other.unionID_;
          onChanged();
        }
        if (other.hasRegisterDate()) {
          setRegisterDate(other.getRegisterDate());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasUserID()) {
          
          return false;
        }
        if (!hasNickName()) {
          
          return false;
        }
        if (!hasHead()) {
          
          return false;
        }
        if (!hasWeChat()) {
          
          return false;
        }
        if (!hasPhone()) {
          
          return false;
        }
        if (!hasNickChangeDate()) {
          
          return false;
        }
        if (!hasUnionID()) {
          
          return false;
        }
        if (!hasRegisterDate()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.game.pb.PlayerMsgProto.UpdatePlayerInfoPB) e.getUnfinishedMessage();
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
       * 用户ID
       * </pre>
       */
      public boolean hasUserID() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 userID = 1;</code>
       *
       * <pre>
       * 用户ID
       * </pre>
       */
      public int getUserID() {
        return userID_;
      }
      /**
       * <code>required int32 userID = 1;</code>
       *
       * <pre>
       * 用户ID
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
       * 用户ID
       * </pre>
       */
      public Builder clearUserID() {
        bitField0_ = (bitField0_ & ~0x00000001);
        userID_ = 0;
        onChanged();
        return this;
      }

      // required string nickName = 2;
      private java.lang.Object nickName_ = "";
      /**
       * <code>required string nickName = 2;</code>
       *
       * <pre>
       * 昵称
       * </pre>
       */
      public boolean hasNickName() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string nickName = 2;</code>
       *
       * <pre>
       * 昵称
       * </pre>
       */
      public java.lang.String getNickName() {
        java.lang.Object ref = nickName_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          nickName_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string nickName = 2;</code>
       *
       * <pre>
       * 昵称
       * </pre>
       */
      public com.google.protobuf.ByteString
          getNickNameBytes() {
        java.lang.Object ref = nickName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          nickName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string nickName = 2;</code>
       *
       * <pre>
       * 昵称
       * </pre>
       */
      public Builder setNickName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        nickName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string nickName = 2;</code>
       *
       * <pre>
       * 昵称
       * </pre>
       */
      public Builder clearNickName() {
        bitField0_ = (bitField0_ & ~0x00000002);
        nickName_ = getDefaultInstance().getNickName();
        onChanged();
        return this;
      }
      /**
       * <code>required string nickName = 2;</code>
       *
       * <pre>
       * 昵称
       * </pre>
       */
      public Builder setNickNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        nickName_ = value;
        onChanged();
        return this;
      }

      // required string head = 3;
      private java.lang.Object head_ = "";
      /**
       * <code>required string head = 3;</code>
       *
       * <pre>
       * 用户头像
       * </pre>
       */
      public boolean hasHead() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required string head = 3;</code>
       *
       * <pre>
       * 用户头像
       * </pre>
       */
      public java.lang.String getHead() {
        java.lang.Object ref = head_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          head_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string head = 3;</code>
       *
       * <pre>
       * 用户头像
       * </pre>
       */
      public com.google.protobuf.ByteString
          getHeadBytes() {
        java.lang.Object ref = head_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          head_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string head = 3;</code>
       *
       * <pre>
       * 用户头像
       * </pre>
       */
      public Builder setHead(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        head_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string head = 3;</code>
       *
       * <pre>
       * 用户头像
       * </pre>
       */
      public Builder clearHead() {
        bitField0_ = (bitField0_ & ~0x00000004);
        head_ = getDefaultInstance().getHead();
        onChanged();
        return this;
      }
      /**
       * <code>required string head = 3;</code>
       *
       * <pre>
       * 用户头像
       * </pre>
       */
      public Builder setHeadBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        head_ = value;
        onChanged();
        return this;
      }

      // required string weChat = 4;
      private java.lang.Object weChat_ = "";
      /**
       * <code>required string weChat = 4;</code>
       *
       * <pre>
       * 微信
       * </pre>
       */
      public boolean hasWeChat() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required string weChat = 4;</code>
       *
       * <pre>
       * 微信
       * </pre>
       */
      public java.lang.String getWeChat() {
        java.lang.Object ref = weChat_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          weChat_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string weChat = 4;</code>
       *
       * <pre>
       * 微信
       * </pre>
       */
      public com.google.protobuf.ByteString
          getWeChatBytes() {
        java.lang.Object ref = weChat_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          weChat_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string weChat = 4;</code>
       *
       * <pre>
       * 微信
       * </pre>
       */
      public Builder setWeChat(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        weChat_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string weChat = 4;</code>
       *
       * <pre>
       * 微信
       * </pre>
       */
      public Builder clearWeChat() {
        bitField0_ = (bitField0_ & ~0x00000008);
        weChat_ = getDefaultInstance().getWeChat();
        onChanged();
        return this;
      }
      /**
       * <code>required string weChat = 4;</code>
       *
       * <pre>
       * 微信
       * </pre>
       */
      public Builder setWeChatBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        weChat_ = value;
        onChanged();
        return this;
      }

      // required string phone = 5;
      private java.lang.Object phone_ = "";
      /**
       * <code>required string phone = 5;</code>
       *
       * <pre>
       * 手机号
       * </pre>
       */
      public boolean hasPhone() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>required string phone = 5;</code>
       *
       * <pre>
       * 手机号
       * </pre>
       */
      public java.lang.String getPhone() {
        java.lang.Object ref = phone_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          phone_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string phone = 5;</code>
       *
       * <pre>
       * 手机号
       * </pre>
       */
      public com.google.protobuf.ByteString
          getPhoneBytes() {
        java.lang.Object ref = phone_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          phone_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string phone = 5;</code>
       *
       * <pre>
       * 手机号
       * </pre>
       */
      public Builder setPhone(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        phone_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string phone = 5;</code>
       *
       * <pre>
       * 手机号
       * </pre>
       */
      public Builder clearPhone() {
        bitField0_ = (bitField0_ & ~0x00000010);
        phone_ = getDefaultInstance().getPhone();
        onChanged();
        return this;
      }
      /**
       * <code>required string phone = 5;</code>
       *
       * <pre>
       * 手机号
       * </pre>
       */
      public Builder setPhoneBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        phone_ = value;
        onChanged();
        return this;
      }

      // required int64 nickChangeDate = 6;
      private long nickChangeDate_ ;
      /**
       * <code>required int64 nickChangeDate = 6;</code>
       *
       * <pre>
       * 上次修改昵称时间
       * </pre>
       */
      public boolean hasNickChangeDate() {
        return ((bitField0_ & 0x00000020) == 0x00000020);
      }
      /**
       * <code>required int64 nickChangeDate = 6;</code>
       *
       * <pre>
       * 上次修改昵称时间
       * </pre>
       */
      public long getNickChangeDate() {
        return nickChangeDate_;
      }
      /**
       * <code>required int64 nickChangeDate = 6;</code>
       *
       * <pre>
       * 上次修改昵称时间
       * </pre>
       */
      public Builder setNickChangeDate(long value) {
        bitField0_ |= 0x00000020;
        nickChangeDate_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 nickChangeDate = 6;</code>
       *
       * <pre>
       * 上次修改昵称时间
       * </pre>
       */
      public Builder clearNickChangeDate() {
        bitField0_ = (bitField0_ & ~0x00000020);
        nickChangeDate_ = 0L;
        onChanged();
        return this;
      }

      // required string unionID = 7;
      private java.lang.Object unionID_ = "";
      /**
       * <code>required string unionID = 7;</code>
       *
       * <pre>
       * unionID
       * </pre>
       */
      public boolean hasUnionID() {
        return ((bitField0_ & 0x00000040) == 0x00000040);
      }
      /**
       * <code>required string unionID = 7;</code>
       *
       * <pre>
       * unionID
       * </pre>
       */
      public java.lang.String getUnionID() {
        java.lang.Object ref = unionID_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          unionID_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string unionID = 7;</code>
       *
       * <pre>
       * unionID
       * </pre>
       */
      public com.google.protobuf.ByteString
          getUnionIDBytes() {
        java.lang.Object ref = unionID_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          unionID_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string unionID = 7;</code>
       *
       * <pre>
       * unionID
       * </pre>
       */
      public Builder setUnionID(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000040;
        unionID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string unionID = 7;</code>
       *
       * <pre>
       * unionID
       * </pre>
       */
      public Builder clearUnionID() {
        bitField0_ = (bitField0_ & ~0x00000040);
        unionID_ = getDefaultInstance().getUnionID();
        onChanged();
        return this;
      }
      /**
       * <code>required string unionID = 7;</code>
       *
       * <pre>
       * unionID
       * </pre>
       */
      public Builder setUnionIDBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000040;
        unionID_ = value;
        onChanged();
        return this;
      }

      // required int64 registerDate = 8;
      private long registerDate_ ;
      /**
       * <code>required int64 registerDate = 8;</code>
       *
       * <pre>
       * 注册时间
       * </pre>
       */
      public boolean hasRegisterDate() {
        return ((bitField0_ & 0x00000080) == 0x00000080);
      }
      /**
       * <code>required int64 registerDate = 8;</code>
       *
       * <pre>
       * 注册时间
       * </pre>
       */
      public long getRegisterDate() {
        return registerDate_;
      }
      /**
       * <code>required int64 registerDate = 8;</code>
       *
       * <pre>
       * 注册时间
       * </pre>
       */
      public Builder setRegisterDate(long value) {
        bitField0_ |= 0x00000080;
        registerDate_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 registerDate = 8;</code>
       *
       * <pre>
       * 注册时间
       * </pre>
       */
      public Builder clearRegisterDate() {
        bitField0_ = (bitField0_ & ~0x00000080);
        registerDate_ = 0L;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.game.proto.UpdatePlayerInfoPB)
    }

    static {
      defaultInstance = new UpdatePlayerInfoPB(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.game.proto.UpdatePlayerInfoPB)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_game_proto_LoginMsgCS_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_game_proto_LoginMsgCS_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_game_proto_LoginMsgSC_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_game_proto_LoginMsgSC_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_game_proto_PlayerInfoPB_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_game_proto_PlayerInfoPB_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_game_proto_UpdatePlayerInfoPB_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_game_proto_UpdatePlayerInfoPB_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\034proto/player/PlayerMsg.proto\022\016com.game" +
      ".proto\"?\n\nLoginMsgCS\022\016\n\006userID\030\001 \002(\005\022\020\n\010" +
      "password\030\002 \002(\t\022\017\n\007channel\030\003 \001(\005\"`\n\nLogin" +
      "MsgSC\022\016\n\006status\030\001 \002(\005\022\017\n\007message\030\002 \002(\t\0221" +
      "\n\013playerInfos\030\003 \001(\0132\034.com.game.proto.Pla" +
      "yerInfoPB\"\234\001\n\014PlayerInfoPB\022\016\n\006userID\030\001 \002" +
      "(\005\022\020\n\010nickName\030\002 \001(\t\022\014\n\004head\030\003 \001(\t\022\016\n\006we" +
      "Chat\030\004 \001(\t\022\r\n\005phone\030\005 \001(\t\022\026\n\016nickChangeD" +
      "ate\030\006 \001(\003\022\017\n\007unionID\030\007 \001(\t\022\024\n\014registerDa" +
      "te\030\010 \001(\003\"\242\001\n\022UpdatePlayerInfoPB\022\016\n\006userI",
      "D\030\001 \002(\005\022\020\n\010nickName\030\002 \002(\t\022\014\n\004head\030\003 \002(\t\022" +
      "\016\n\006weChat\030\004 \002(\t\022\r\n\005phone\030\005 \002(\t\022\026\n\016nickCh" +
      "angeDate\030\006 \002(\003\022\017\n\007unionID\030\007 \002(\t\022\024\n\014regis" +
      "terDate\030\010 \002(\003B\035\n\013com.game.pbB\016PlayerMsgP" +
      "roto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_com_game_proto_LoginMsgCS_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_com_game_proto_LoginMsgCS_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_game_proto_LoginMsgCS_descriptor,
              new java.lang.String[] { "UserID", "Password", "Channel", });
          internal_static_com_game_proto_LoginMsgSC_descriptor =
            getDescriptor().getMessageTypes().get(1);
          internal_static_com_game_proto_LoginMsgSC_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_game_proto_LoginMsgSC_descriptor,
              new java.lang.String[] { "Status", "Message", "PlayerInfos", });
          internal_static_com_game_proto_PlayerInfoPB_descriptor =
            getDescriptor().getMessageTypes().get(2);
          internal_static_com_game_proto_PlayerInfoPB_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_game_proto_PlayerInfoPB_descriptor,
              new java.lang.String[] { "UserID", "NickName", "Head", "WeChat", "Phone", "NickChangeDate", "UnionID", "RegisterDate", });
          internal_static_com_game_proto_UpdatePlayerInfoPB_descriptor =
            getDescriptor().getMessageTypes().get(3);
          internal_static_com_game_proto_UpdatePlayerInfoPB_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_game_proto_UpdatePlayerInfoPB_descriptor,
              new java.lang.String[] { "UserID", "NickName", "Head", "WeChat", "Phone", "NickChangeDate", "UnionID", "RegisterDate", });
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
