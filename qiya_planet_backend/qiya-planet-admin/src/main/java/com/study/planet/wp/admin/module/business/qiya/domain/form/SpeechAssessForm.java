package com.study.planet.wp.admin.module.business.qiya.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class SpeechAssessForm {

    @Schema(description = "参考文本，必须是单词、例句或诗句")
    @NotBlank(message = "要读的内容不能为空")
    @Length(max = 80, message = "一次只评一句")
    private String refText;

    @Schema(description = "wav 或 mp3 的 base64，不含 data: 前缀")
    @NotBlank(message = "没有听到录音")
    private String audioBase64;

    @Schema(description = "wav 或 mp3")
    @Length(max = 8, message = "音频格式不正确")
    private String audioFormat;

    @Schema(description = "english 或 chinese")
    @Length(max = 16, message = "科目不正确")
    private String subject;

    @Schema(description = "word、sentence 或 poem")
    @Length(max = 16, message = "评测类型不正确")
    private String evalKind;

    @Schema(description = "年级档，用来决定要不要指出薄弱音")
    @Length(max = 16, message = "年级不正确")
    private String gradeKey;
}