## Magisk (5f2e22a2-alpha)
- 基于5f2e22a2，相关修改参考上游更新日志
- 合并zip至apk。将apk的后缀改为zip即可在TWRP刷入。卸载包通过app的保存卸载包功能导出。如果愿意手动，删除apk里面的`META-INF/com/google/android/updater-script`，即成为卸载包。
- 如果可行，Magisk Hide将会处理隔离服务。

## Magisk Manager (5f2e22a2-alpha)
- 基于5f2e22a2，相关修改参考上游更新日志
- 正确处理来自magiskd的任何数据
- 支持SharedUserId
- 添加不支持的环境检测：安装为系统应用或存在其它su
- 还原boot镜像后删除备份文件
- 合并zip至apk。
- 提升target API 到 Android 11
