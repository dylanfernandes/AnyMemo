package org.liberty.android.fantastischmemo.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.greenrobot.eventbus.EventBus;
import org.liberty.android.fantastischmemo.common.AMApplication;
import org.liberty.android.fantastischmemo.converter.CSVExporter;
import org.liberty.android.fantastischmemo.converter.CSVImporter;
import org.liberty.android.fantastischmemo.converter.Converter;
import org.liberty.android.fantastischmemo.converter.Mnemosyne2CardsExporter;
import org.liberty.android.fantastischmemo.converter.Mnemosyne2CardsImporter;
import org.liberty.android.fantastischmemo.converter.MnemosyneXMLExporter;
import org.liberty.android.fantastischmemo.converter.MnemosyneXMLImporter;
import org.liberty.android.fantastischmemo.converter.QATxtExporter;
import org.liberty.android.fantastischmemo.converter.QATxtImporter;
import org.liberty.android.fantastischmemo.converter.Supermemo2008XMLImporter;
import org.liberty.android.fantastischmemo.converter.SupermemoXMLImporter;
import org.liberty.android.fantastischmemo.converter.TabTxtExporter;
import org.liberty.android.fantastischmemo.converter.TabTxtImporter;
import org.liberty.android.fantastischmemo.converter.ZipExporter;
import org.liberty.android.fantastischmemo.converter.ZipImporter;
import org.liberty.android.fantastischmemo.scheduler.DefaultScheduler;
import org.liberty.android.fantastischmemo.scheduler.Scheduler;
import org.liberty.android.fantastischmemo.utils.AMFileUtil;
import org.liberty.android.fantastischmemo.utils.DatabaseUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import okhttp3.OkHttpClient;

@Module
public class AppModules {

    private AMApplication application;

    public AppModules(AMApplication app) {
        this.application = app;
    }

    @Provides
    @PerApplication
    AMApplication providesApplication() {
        return application;
    }

    @Provides
    @PerApplication
    @ForApplication
    Context providesContext() {
        return application;
    }

    @Provides
    @PerApplication
    Scheduler providesScheduler(DefaultScheduler scheduler) {
        return scheduler;
    }

    @Provides
    @PerApplication
    ExecutorService provideExecutorService() {
        return Executors.newFixedThreadPool(4);
    }

    @Provides
    @PerApplication
    SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @PerApplication
    OkHttpClient providesOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @PerApplication
    EventBus providesEventBus() {
        return new EventBus();
    }

    @Provides
    @PerApplication
    @IntoMap
    @ClassKey(CSVExporter.class)
    Converter providesCSVExporter() {
        return new CSVExporter();
    }

    @Provides
    @PerApplication
    @IntoMap
    @ClassKey(CSVImporter.class)
    Converter providesCSVImporter(DatabaseUtil databaseUtil) {
        return new CSVImporter(databaseUtil);
    }

    @Provides
    @PerApplication
    @IntoMap
    @ClassKey(Mnemosyne2CardsExporter.class)
    Converter providesMnemosyne2CardsExporter(AMFileUtil amFileUtil) {
        return new Mnemosyne2CardsExporter(amFileUtil);
    }

    @Provides
    @PerApplication
    @IntoMap
    @ClassKey(Mnemosyne2CardsImporter.class)
    Converter providesMnemosyne2CardsImporter(DatabaseUtil databaseUtil) {
        return new Mnemosyne2CardsImporter(databaseUtil);
    }

    @Provides
    @PerApplication
    @IntoMap
    @ClassKey(MnemosyneXMLExporter.class)
    Converter providesMnemosyneXMLExporter() {
        return new MnemosyneXMLExporter();
    }

    @Provides
    @PerApplication
    @IntoMap
    @ClassKey(MnemosyneXMLImporter.class)
    Converter providesMnemosyneXMLImporter(DatabaseUtil databaseUtil) {
        return new MnemosyneXMLImporter(databaseUtil);
    }

    @Provides
    @PerApplication
    @IntoMap
    @ClassKey(QATxtExporter.class)
    Converter providesQATxtExporter() {
        return new QATxtExporter();
    }

    @Provides
    @PerApplication
    @IntoMap
    @ClassKey(QATxtImporter.class)
    Converter providesQATxtImporter(DatabaseUtil databaseUtil) {
        return new QATxtImporter(databaseUtil);
    }

    @Provides
    @PerApplication
    @IntoMap
    @ClassKey(Supermemo2008XMLImporter.class)
    Converter providesSupermemo2008XMLImporter() {
        return new Supermemo2008XMLImporter();
    }

    @Provides
    @PerApplication
    @IntoMap
    @ClassKey(SupermemoXMLImporter.class)
    Converter providesSupermemoXMLImporter(DatabaseUtil databaseUtil) {
        return new SupermemoXMLImporter(databaseUtil);
    }

    @Provides
    @PerApplication
    @IntoMap
    @ClassKey(TabTxtExporter.class)
    Converter providesTabTxtExporter() {
        return new TabTxtExporter();
    }

    @Provides
    @PerApplication
    @IntoMap
    @ClassKey(TabTxtImporter.class)
    Converter providesTabTxtImporter(DatabaseUtil databaseUtil) {
        return new TabTxtImporter(databaseUtil);
    }

    @Provides
    @PerApplication
    @IntoMap
    @ClassKey(ZipExporter.class)
    Converter providesZipExporter() {
        return new ZipExporter();
    }

    @Provides
    @PerApplication
    @IntoMap
    @ClassKey(ZipImporter.class)
    Converter providesZipImporter() {
        return new ZipImporter();
    }
}
